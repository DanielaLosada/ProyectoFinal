package co.edu.uniquindio.proyecto.dto.ReservaDTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ActualizarReservaDTO(
        String idReserva,
        @NotNull LocalDateTime fecha,
        @NotNull LocalTime hora
) {
}
