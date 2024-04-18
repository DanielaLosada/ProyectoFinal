package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import com.mongodb.assertions.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteServicioTest {
    @Autowired
    private ClienteServicio clienteServicio;

    @Test
    public void registrarTest() throws Exception{
    //Se crea un objeto de tipo RegistroClienteDTO
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "Juan",
                "mi foto",
                "juanito",
                "juan@email.com",
                "mipassword",
                "Armenia"
        );
    //Se registra el cliente
        String codigo = clienteServicio.registrarCliente(registroClienteDTO);
        //Se verifica que el código no sea nulo, es decir, que se haya registrado el cliente
        Assertions.assertNotNull(codigo);
    }
}
