package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotNull;

public record ItemModeradorDTO(
        @NotNull String idModerador,
        String nombre
) {
}
