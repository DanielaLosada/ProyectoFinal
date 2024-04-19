package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AutenticacionServicioTest {
    @Autowired
    AutenticacionServicio autenticacionServicio;

    @Test
    public void iniciarSesionClienteTest() throws Exception{
        LoginDTO loginDTO = new LoginDTO(
                "daniela2444losada@gmail.com",
                "lunes123"
        );
        TokenDTO codigo = autenticacionServicio.iniciarSesionCliente(loginDTO);
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void iniciarSesionModeradorTest() throws Exception{
        LoginDTO loginDTO = new LoginDTO(
                "ximena26RuizR@gmail.com",
                "lucas2004"
        );
        TokenDTO codigo = autenticacionServicio.iniciarSesionModerador(loginDTO);
        Assertions.assertNotNull(codigo);
    }
}
