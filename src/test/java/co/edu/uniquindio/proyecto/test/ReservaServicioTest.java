package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.RegistroReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ActualizarReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.RegistroReservaDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReseniaServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReservaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
public class ReservaServicioTest {
    @Autowired
    ReservaServicio reservaServicio;
    @Autowired
    ClienteServicio clienteServicio;

    @Test
    public void crearReservaTest() throws Exception{
        RegistroReservaDTO registroReservaDTO = new RegistroReservaDTO(
                "66229ef257c6c37d8b970b4e",
                "6622a1cb66aafa3801ec15f0",
                LocalDateTime.now(),
                LocalTime.now(),
                150.000
        );
        String codigo = clienteServicio.crearReserva(registroReservaDTO);
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void actualizarTest() throws Exception {
        ActualizarReservaDTO actualizarReservaDTO = new ActualizarReservaDTO(
                "6622d12ae68d010afbded894",
                LocalDateTime.now(),
                LocalTime.now()
        );
        reservaServicio.actualizarReserva(actualizarReservaDTO);
    }

    @Test
    public void eliminarTest() throws Exception{
        reservaServicio.eliminarReserva("6622d0ae37da2e2db11f881d");
        Assertions.assertThrows(Exception.class, () -> {
            reservaServicio.eliminarReserva("6622d0ae37da2e2db11f881d");
        });
    }

    @Test
    public void listarReservasXNegocioTest() throws Exception{
        Assertions.assertNotNull(reservaServicio.listarReservasXNegocio("6622a1cb66aafa3801ec15f0"));
    }
    @Test
    public void listarReservasXCliente() throws Exception{
        Assertions.assertNotNull(reservaServicio.listarReservasXCliente("66229ef257c6c37d8b970b4e"));
    }

}
