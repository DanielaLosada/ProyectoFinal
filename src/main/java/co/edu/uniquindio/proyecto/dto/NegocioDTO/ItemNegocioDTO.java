package co.edu.uniquindio.proyecto.dto.NegocioDTO;

import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ItemNegocioDTO(
        @NotBlank String id,
        @NotBlank String nombre,
        @NotBlank List<String> listaImagenes,
        @NotBlank TipoNegocio tipoNegocio,

        @NotBlank Ubicacion ubicacion

) {
}
