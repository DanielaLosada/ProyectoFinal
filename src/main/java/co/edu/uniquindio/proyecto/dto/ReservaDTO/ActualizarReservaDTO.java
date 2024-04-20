package co.edu.uniquindio.proyecto.dto.ReservaDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ActualizarReservaDTO(
        LocalDateTime fecha,
        LocalTime hora
) {
}
