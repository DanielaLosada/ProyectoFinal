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
    Ubicacion ubicacion = new Ubicacion(246.0,246.0);

    @Test
    public void crearNegocioTest() throws Exception{

        List<String> listImagenes = new ArrayList<>();
        List<String> listTele = new ArrayList<>();
        List<Horario> horarioNegocio = new ArrayList<>();
        List<HistorialRevision> listHistorial = new ArrayList<>();

        RegistroNegocioDTO registroNegocioDTO = new RegistroNegocioDTO(
                "Le COCO",
                "Fino, finisimo",
                listImagenes,
                listTele,
                ubicacion,
               "66229ef257c6c37d8b970b4e",
                horarioNegocio,
                TipoNegocio.RESTAURANTE,
                listHistorial,
                "Armenia Norte"
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
                "66229f2fdcbd8f635252f497",
                "Restaurante Comida Mexicana",
                "Restaurante de la mejor comida Mexicana",
                imagenes,
                telefonos,
                horarioNegocio
        );
    }

    @Test
    public void eliminarTest() throws Exception{
        negocioServicio.eliminarNegocio("66229f2fdcbd8f635252f497");
        Assertions.assertThrows(Exception.class, () -> {
            negocioServicio.eliminarNegocio("66229f2fdcbd8f635252f497");
        });
    }

    @Test
    public void buscarNegocioTest() throws Exception{
        Assertions.assertNotNull(negocioServicio.buscarNegocios("66229f2fdcbd8f635252f497","66229ef257c6c37d8b970b4e"));
    }

    @Test
    public void buscarNegocioNombreTest() throws Exception{
        Assertions.assertNotNull(negocioServicio.filtarNegocioNombre("Burguer City"));
    }

    @Test
    public void buscarTipoTest() throws Exception{
        Assertions.assertNotNull(negocioServicio.filtarNegocioTipo(TipoNegocio.RESTAURANTE));
    }

    @Test
    public void filtrarEstado() throws Exception{
        Assertions.assertNotNull(negocioServicio.filtrarNegocioEstado(EstadoNegocio.PENDIENTE));
    }

    @Test
    public void listarNegociosUsuarioTest() throws Exception{
        Assertions.assertNotNull(negocioServicio.listarNegocioPropietario("66229ef257c6c37d8b970b4e"));
    }

}
