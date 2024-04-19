package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ReseniaDTO.RegistroReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ResponderReseniaDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReseniaServicio;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ReseniaServicioTest {

    @Autowired
    ReseniaServicio reseniaServicio;

    @Test
    public void crearResenia() throws Exception{
        List<String> imagenes = new ArrayList<>();
        RegistroReseniaDTO registroReseniaDTO = new RegistroReseniaDTO(
                "Muy bueno",
                5,
                "Usuario2",
                "Negocio3",
                ""
        );

        String codigo = reseniaServicio.crearResenia(registroReseniaDTO);
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void responderResenia() throws Exception{

        ResponderReseniaDTO responderReseniaDTO = new ResponderReseniaDTO(
                "661d620c5ea25a30e85b6cfd",
                "Gracias"
        );
        reseniaServicio.responderResenia(responderReseniaDTO);
        Assertions.assertNotNull(Exception.class, responderReseniaDTO::idComentario);
    }
}
