package co.edu.uniquindio.proyecto.dto.ClienteDTO;

import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalleClienteDTO(

        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank String nickname,
        @NotBlank String email,
        @NotBlank String ciudadResidencia,
        @NotNull Ubicacion ubicacion

) {
}
