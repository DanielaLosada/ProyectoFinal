package co.edu.uniquindio.proyecto.dto.ReservaDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ActualizarReservaDTO(

        String codigo,
        LocalDateTime fecha,
        LocalTime hora
) {
}
