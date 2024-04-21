package co.edu.uniquindio.proyecto.dto.NegocioDTO;

import co.edu.uniquindio.proyecto.modelo.HistorialRevision;
import co.edu.uniquindio.proyecto.modelo.Horario;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record DetalleNegocioDTO(
        @NotBlank String id,
        @NotBlank String nombre,
        @NotBlank @Length(max = 100) String descripcion,
        @NotNull List<String> listaImagenes,
        @NotNull List<String> listaTelefonos,
        @NotNull Ubicacion ubicacion,
        @NotBlank String idUsuario,
        @NotNull List<Horario> horarioNegocio,
        @NotNull TipoNegocio tipoNegocio,
        List<HistorialRevision> historialRevision

) {
}
