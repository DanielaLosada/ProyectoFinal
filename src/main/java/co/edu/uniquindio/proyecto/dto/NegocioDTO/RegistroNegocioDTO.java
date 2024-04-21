package co.edu.uniquindio.proyecto.dto.NegocioDTO;

import co.edu.uniquindio.proyecto.modelo.HistorialRevision;
import co.edu.uniquindio.proyecto.modelo.Horario;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroNegocioDTO(
        @NotBlank String nombre,
        @NotBlank @Length(max = 100) String descripcion,
        @NotEmpty List<String> listImagenes,
        @NotEmpty List<String> listTelefonos,
        @NotNull Ubicacion ubicacion,
        @NotBlank String codigoCliente,
        @NotEmpty List <Horario> listHorarios,
        @NotNull TipoNegocio tipoNegocio,
        List<HistorialRevision> historialNegocio,
        @NotBlank String direccion
) {

}
