package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;
    Ubicacion ubicacion = new Ubicacion(246,2456);

    @Test
    public void registrarTest() throws Exception {
        //Se crea un objeto de tipo RegistroClienteDTO
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "Daniela",
                "2030202",
                "danielita2",
                "danielalosada202@gmail.com",
                "eorjg2",
                "Armenia"
        );
        //Se registra el cliente
        String codigo = clienteServicio.registrarCliente(registroClienteDTO);
        //Se verifica que el codigo no sea nulo, es decir, que se haya registrado el cliente
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void actualizarTest() throws Exception{
        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "Usuario1",
                "Isabel",
                "342342",
                "danielalosada202@gmail.com",
                "Calarca"
        );
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente("Usuario1");
        Assertions.assertNotNull("urlFotoperfil",detalleClienteDTO.fotoPerfil());
    }

    @Test
    public void eliminarTest() throws Exception{
        clienteServicio.eliminarCliente("Usuario1");
        Assertions.assertThrows(Exception.class, () -> {
            clienteServicio.obtenerCliente("Usuario1");
        });
    }

    @Test
    public void obtenerTest() throws Exception{
        DetalleClienteDTO detalleUsuarioDTO = clienteServicio.obtenerCliente("Usuario1");
        Assertions.assertNotNull(detalleUsuarioDTO.codigo());
    }

    @Test
    public void agregarNegecioFavTest() throws Exception{
        clienteServicio.agregarNegocioFavorito("Usuario1","Negocio1");
    }

    @Test
    public void eliminarNegocioFavTest() throws Exception{
        clienteServicio.eliminarNegocioFavorito("Usuario1","Negocio1");
    }

    @Test
    public void recomendarLugarTest() throws Exception{
        Assertions.assertThrows(Exception.class, () -> {
            clienteServicio.recomendarLugares("Usuario1");
        });
    }

    @Test
    public void listarNegociosFav() throws Exception{
        Assertions.assertNotNull(clienteServicio.listarNegociosFavoritos("Usuario1"));
    }

    @Test
    public void actualizarUbicacionTest() throws Exception{
        clienteServicio.actualizarUbicacion("Usuario1",300,350);
    }

}
