package co.edu.uniquindio.proyecto.dto.ReservaDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ItemReservaDTO(
        String codigo,
        String codigoCliente,
        String codigoNegocio,
        LocalDateTime fecha,
        LocalTime hora

) {
}
