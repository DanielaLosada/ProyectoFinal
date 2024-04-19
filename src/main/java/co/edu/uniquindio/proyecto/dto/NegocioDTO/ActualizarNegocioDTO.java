package co.edu.uniquindio.proyecto.dto.NegocioDTO;

import co.edu.uniquindio.proyecto.modelo.Horario;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarNegocioDTO(
        String id,
        @NotBlank String nombre,
        @NotBlank @Length(max = 100) String descripcion,
        @NotBlank List<String> listImagenes,
        @NotBlank List<String> listTelefonos,
        @NotBlank List<Horario> horarioNegocio
) {
}
