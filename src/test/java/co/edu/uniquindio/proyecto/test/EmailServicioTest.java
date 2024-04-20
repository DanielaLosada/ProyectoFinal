package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class EmailServicioTest {

    @Autowired
    EmailService emailServicio;

    @Test
    public void enviarTest() throws Exception{

        EmailDTO emailDTO = new EmailDTO(
                "Test",
                "Probando test para envio de emails",
                "danielalosada202@gmail.com"
        );
        emailServicio.enviarCorreo(emailDTO);
        Assertions.assertNotNull("asunto",emailDTO.asunto());
    }
}
