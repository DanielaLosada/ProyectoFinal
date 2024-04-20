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
        RegistroReseniaDTO registroReseniaDTO = new RegistroReseniaDTO(
                "regulas 3/4",
                4,
                "66229ef257c6c37d8b970b4e",
                "6622a1ee2b00de270d9d23e3",
                ""
        );
        String codigo = reseniaServicio.crearResenia(registroReseniaDTO);
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void responderResenia() throws Exception{

        ResponderReseniaDTO responderReseniaDTO = new ResponderReseniaDTO(
                "6622aa837a9f3044d9b9dc43",
                "Gracias"
        );
        reseniaServicio.responderResenia(responderReseniaDTO);
        Assertions.assertNotNull(Exception.class, responderReseniaDTO::idComentario);
    }
}
