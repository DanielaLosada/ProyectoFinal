package co.edu.uniquindio.proyecto.dto.ReseniaDTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ItemReseniaDTO(
        String id,
        String descripcion,
        String idCliente,
        int calificacion,
        String respuesta,
        String idNegocio
) {

}
