package co.edu.uniquindio.proyecto.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString

public class HistorialRevision {

    private String descripcion;
    private String codigoModerador;
    private LocalDateTime fecha;
    private EstadoNegocio estado;
}
