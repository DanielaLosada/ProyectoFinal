package co.edu.uniquindio.proyecto.dto.NegocioDTO;

import co.edu.uniquindio.proyecto.modelo.HistorialRevision;
import co.edu.uniquindio.proyecto.modelo.Horario;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record DetalleNegocioDTO(
        @NotBlank String id,
        @NotBlank String nombre,
        @NotBlank @Length(max = 100) String descripcion,
        @NotBlank List<String> listaImagenes,
        @NotBlank List<String> listaTelefonos,
        @NotBlank Ubicacion ubicacion,
        @NotBlank String idUsuario,
        @NotBlank List<Horario> horarioNegocio,
        @NotBlank TipoNegocio tipoNegocio,
        List<HistorialRevision> historialRevision

) {
}
