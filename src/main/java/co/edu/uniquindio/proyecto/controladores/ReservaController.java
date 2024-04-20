package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RegistroNegocioDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ActualizarReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ItemReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.RegistroReservaDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReservaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserva")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaServicio reservaServicio;
    private final ClienteServicio clienteServicio;

    @PostMapping("/registrar-reserva")
    public ResponseEntity<MensajeDTO<String>> crearReserva(@Valid @RequestBody RegistroReservaDTO registroReservaDTO, String idCliente, String idNegocio) throws Exception{
        clienteServicio.crearReserva(registroReservaDTO, idCliente, idNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"La reserva se creo correctamente"));
    }

    @DeleteMapping("/eliminar-reserva/{idReserva}")
    public ResponseEntity<MensajeDTO<String>> eliminarReserva(@PathVariable String idReserva) throws Exception{
        reservaServicio.eliminarReserva(idReserva);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Reserva eliminado correctamente."));
    }

    @PutMapping("/editar-reserva/{idReserva}")
    public ResponseEntity<MensajeDTO<String>> actualizarReserva(@Valid @RequestBody ActualizarReservaDTO actualizarReservaDTO, String idReserva) throws Exception{
        reservaServicio.actualizarReserva(actualizarReservaDTO, idReserva);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Reserva actualizado con éxito."));
    }

    @GetMapping("/listar-reservas-usuario/{idCliente}")
    public ResponseEntity<MensajeDTO<List<ItemReservaDTO>>> listarReservasXCliente(@PathVariable String idCliente) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false,reservaServicio.listarReservasXCliente(idCliente)));
    }

    @GetMapping("/listar-reservas-negocio/{idCliente}")
    public ResponseEntity<MensajeDTO<List<ItemReservaDTO>>> listarReservasXNegocio(@PathVariable String idNegocio) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false,reservaServicio.listarReservasXNegocio(idNegocio)));
    }

}
