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
                "pedrolopez@gmail.com",
                "Paolooo@0"
        );
        TokenDTO codigo = autenticacionServicio.iniciarSesionCliente(loginDTO);
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void iniciarSesionModeradorTest() throws Exception{
        LoginDTO loginDTO = new LoginDTO(
                "angieruiz564@gmail.com",
                "Lucas2004@"
        );
        TokenDTO codigo = autenticacionServicio.iniciarSesionModerador(loginDTO);
        Assertions.assertNotNull(codigo);
    }
}
