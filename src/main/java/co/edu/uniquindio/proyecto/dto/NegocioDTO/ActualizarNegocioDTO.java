package co.edu.uniquindio.proyecto.dto.NegocioDTO;

import co.edu.uniquindio.proyecto.modelo.Horario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarNegocioDTO(
        String id,
        @NotBlank String nombre,
        @NotBlank @Length(max = 100) String descripcion,
        @NotNull List<String> listImagenes,
        @NotNull List<String> listTelefonos,
        @NotNull List<Horario> horarioNegocio
) {
}
