package co.edu.uniquindio.proyecto.dto.ReseniaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroReseniaDTO(@NotBlank String descripcion,
                                 @NotNull int calificacion,
                                 @NotBlank String idUsuario,
                                 @NotBlank String idNegocio,
                                 String respuesta
) {
}
