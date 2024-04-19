package co.edu.uniquindio.proyecto.dto.ReseniaDTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ReseniaDTO(@NotBlank String id,
                         @NotBlank String descripcion,
                         @NotBlank String idCliente,
                         @NotBlank String idNegocio,
                         String respuesta,
                         @NotBlank LocalDateTime fechaComentario) {
}

