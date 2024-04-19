package co.edu.uniquindio.proyecto.dto.ReservaDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record RegistroReservaDTO(
        @NotBlank @Length(max = 100) String codigo,
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio,
        @NotBlank LocalDateTime fecha,
        @NotBlank LocalTime hora,
        @NotBlank Double costo) {
}
