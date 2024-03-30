package co.edu.uniquindio.proyecto.dto;

public record CambioPasswordDTO(
        String passwordNueva,
        String id,
        String token
) {
}