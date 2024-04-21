package co.edu.uniquindio.proyecto.dto.ReservaDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record RegistroReservaDTO(
        @NotNull String codigoCliente,
        @NotNull String codigoNegocio,
        @NotNull LocalDateTime fecha,
        @NotNull LocalTime hora,
        @NotNull Double costo) {
}
