package co.edu.uniquindio.proyecto.dto.ReseniaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReseniaDTO(@NotBlank String id,
                         @NotBlank String descripcion,
                         @NotBlank String idCliente,
                         @NotBlank String idNegocio,
                         String respuesta,
                         @NotNull LocalDateTime fechaComentario) {
}

