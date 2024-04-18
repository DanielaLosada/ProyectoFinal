package co.edu.uniquindio.proyecto.dto.ClienteDTO;

public record CambioPasswordDTO(
        String passwordNueva,
        String id,
        String token
) {
}