package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RegistroNegocioDTO;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class NegocioServicioTest {
    @Autowired
    private NegocioServicio negocioServicio;
    Ubicacion ubicacion = new Ubicacion(246,246);

    @Test
    public void crearNegocioTest() throws Exception{

        List<String> listImagenes = new ArrayList<>();
        List<String> listTele = new ArrayList<>();
        List<Horario> horarioNegocio = new ArrayList<>();
        List<HistorialRevision> listHistorial = new ArrayList<>();

        RegistroNegocioDTO registroNegocioDTO = new RegistroNegocioDTO(
                "Don platano",
                "Restaurante nuevo",
                listImagenes,
                listTele,
                ubicacion,
               "Usuario1",
                horarioNegocio,
                TipoNegocio.RESTAURANTE,
                listHistorial,
                "Armenia"
        );

        String codigo = negocioServicio.crearNegocio(registroNegocioDTO);

        Assertions.assertNotNull(codigo);

    }
    @Test
    public void actualizarTest() throws Exception{

        List<String> imagenes = new ArrayList<>();
        List<String> telefonos = new ArrayList<>();
        List<Horario> horarioNegocio=new ArrayList<>();

        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "Negocio1",
                "Restaurante Comida Mexicana",
                "Restaurante de la mejor comida Mexicana",
                imagenes,
                telefonos,
                horarioNegocio
        );

        negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        DetalleNegocioDTO detalleNegocioDTO = negocioServicio.buscarNegocios("Negocio1","Usuario1");
        Assertions.assertNotNull("id",detalleNegocioDTO.id());
    }

    @Test
    public void eliminarTest() throws Exception{
        negocioServicio.eliminarNegocio("Negocio2");
        Assertions.assertThrows(Exception.class, () -> {
            negocioServicio.eliminarNegocio("Negocio2");
        });
    }

    @Test
    public void buscarNegocioTest() throws Exception{
        Assertions.assertNotNull(negocioServicio.buscarNegocios("Negocio1","Usuario1"));
    }

    @Test
    public void buscarNegocioNombreTest() throws Exception{
        Assertions.assertNotNull(negocioServicio.filtarNegocioNombre("Restaurante Mexicano"));
    }

    @Test
    public void buscarTipoTest() throws Exception{
        Assertions.assertNotNull(negocioServicio.filtarNegocioTipo(TipoNegocio.RESTAURANTE));
    }

    @Test
    public void filtrarEstado() throws Exception{
        Assertions.assertNotNull(negocioServicio.filtrarNegocioEstado(EstadoNegocio.RECHAZADO));
    }

    @Test
    public void listarNegociosUsuarioTest() throws Exception{
        Assertions.assertNotNull(negocioServicio.listarNegocioPropietario("Usuario1"));
    }


}
