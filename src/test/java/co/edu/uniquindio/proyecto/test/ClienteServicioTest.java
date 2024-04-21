package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.TipoMedioTransporte;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;
    Ubicacion ubicacion = new Ubicacion(246.0,2456.0);

    @Test
    public void registrarTest() throws Exception {
        //Se crea un objeto de tipo RegistroClienteDTO
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "Samuel",
                "jpg",
                "samuel123",
                "samuellopez@gmail.com",
                "Miercoles1@23",
                "Ibague"
        );
        //Se registra el cliente
        String codigo = clienteServicio.registrarCliente(registroClienteDTO);
        //Se verifica que el codigo no sea nulo, es decir, que se haya registrado el cliente
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void actualizarTest() throws Exception{
        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "66229a0f7417a33eb86739e9",
                "Isabel",
                "342342",
                "danielalosada202@gmail.com",
                "Calarca"
        );
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente("66229a0f7417a33eb86739e9");
        Assertions.assertNotNull("urlFotoperfil",detalleClienteDTO.fotoPerfil());
    }

    @Test
    public void eliminarTest() throws Exception{
        clienteServicio.eliminarCliente("66229a0f7417a33eb86739e9");
        Assertions.assertThrows(Exception.class, () -> {
            clienteServicio.obtenerCliente("66229a0f7417a33eb86739e9");
        });
    }

    @Test
    public void obtenerTest() throws Exception{
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente("66229a0f7417a33eb86739e9");
        Assertions.assertNotNull(detalleClienteDTO.codigo());
    }

    @Test
    public void agregarNegecioFavTest() throws Exception{
        clienteServicio.agregarNegocioFavorito("66229ef257c6c37d8b970b4e","6622a1ee2b00de270d9d23e3");
    }

    @Test
    public void eliminarNegocioFavTest() throws Exception{
        clienteServicio.eliminarNegocioFavorito("66229ef257c6c37d8b970b4e","6622a1cb66aafa3801ec15f0");
    }

    @Test
    public void recomendarLugarTest() throws Exception{
        Assertions.assertThrows(Exception.class, () -> {
            clienteServicio.recomendarLugares("66229a0f7417a33eb86739e9");
        });
    }


    @Test
    public void recuperarContraseniaTest() throws Exception{

        Assertions.assertNotNull(clienteServicio.recuperarContrasenia("6623f76a5defc33af59528ca"));

    }

    @Test
    public void listarNegociosFav() throws Exception{
        Assertions.assertNotNull(clienteServicio.listarNegociosFavoritos("66229ef257c6c37d8b970b4e"));
    }

    @Test
    public void actualizarUbicacionTest() throws Exception{
        clienteServicio.actualizarUbicacion("66229ef257c6c37d8b970b4e",300.0,350.0);
    }

    @Test
    public void solicitarRutaTest() throws ResourceNotFoundException {
        Ubicacion ubicacionDestino = new Ubicacion(DESTINO_LATITUD, DESTINO_LONGITUD);
        Ubicacion ubicacionUsuario = new Ubicacion(USUARIO_LATITUD, USUARIO_LONGITUD);

        Double distancia = clienteServicio.solicitarRuta("66229a0f7417a33eb86739e9", ubicacionDestino, TipoMedioTransporte.CARRO);

        Double distanciaEsperada = calcularDistancia(ubicacionUsuario.getLatitud(), ubicacionUsuario.getLongitud(), ubicacionDestino.getLatitud(), ubicacionDestino.getLongitud());
        Assertions.assertEquals(distanciaEsperada, distancia);
    }

    private Double calcularDistancia(Double latitudOrigen, Double longitudOrigen, Double latitudDestino, Double longitudDestino) {
        final int RADIO_TIERRA = 6371; // Radio de la Tierra en kilómetros
        Double latitudUsuarioRad = Math.toRadians(latitudOrigen);
        Double longitudUsuarioRad = Math.toRadians(longitudOrigen);
        Double latitudNegocioRad = Math.toRadians(latitudDestino);
        Double longitudNegocioRad = Math.toRadians(longitudDestino);
        Double diferenciaLatitud = latitudNegocioRad - latitudUsuarioRad;
        Double diferenciaLongitud = longitudNegocioRad - longitudUsuarioRad;
        Double a = Math.pow(Math.sin(diferenciaLatitud / 2), 2) +
                Math.cos(latitudUsuarioRad) * Math.cos(latitudNegocioRad) *
                        Math.pow(Math.sin(diferenciaLongitud / 2), 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distancia = RADIO_TIERRA * c;
        return distancia;
    }

    private static final Double DESTINO_LATITUD = 123.456;
    private static final Double DESTINO_LONGITUD = 789.012;
    private static final Double USUARIO_LATITUD = 456.789;
    private static final Double USUARIO_LONGITUD = 987.654;


}
