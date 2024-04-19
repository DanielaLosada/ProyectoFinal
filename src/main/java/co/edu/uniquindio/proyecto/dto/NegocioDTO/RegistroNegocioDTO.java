package co.edu.uniquindio.proyecto.dto.NegocioDTO;

import co.edu.uniquindio.proyecto.modelo.HistorialRevision;
import co.edu.uniquindio.proyecto.modelo.Horario;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroNegocioDTO(
        @NotBlank String nombre,
        @NotBlank @Length(max = 100) String descripcion,
        @NotBlank List<String> listImagenes,
        @NotBlank List<String> listTelefonos,

        @NotBlank String idCliente,
        @NotBlank Ubicacion ubicacion,
        @NotBlank String codigoCliente,
        @NotBlank List <Horario> listHorarios,
        @NotBlank TipoNegocio tipoNegocio,
        List<HistorialRevision> historialNegocio,
        @NotBlank String direccion
) {

}
