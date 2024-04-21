package co.edu.uniquindio.proyecto.dto.NegocioDTO;

import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ItemNegocioDTO(
        @NotBlank String id,
        @NotBlank String nombre,
        @NotNull List<String> listaImagenes,
        @NotNull TipoNegocio tipoNegocio,
        @NotNull Ubicacion ubicacion

) {
}
