package co.edu.uniquindio.proyecto.dto.ReseniaDTO;

import jakarta.validation.constraints.NotBlank;

public record RegistroReseniaDTO(@NotBlank String descripcion,
                                 @NotBlank int calificacion,
                                 @NotBlank String idUsuario,
                                 @NotBlank String idNegocio,
                                 String respuesta
) {
}
