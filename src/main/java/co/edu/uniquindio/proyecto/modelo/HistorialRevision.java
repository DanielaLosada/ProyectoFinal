package co.edu.uniquindio.proyecto.modelo;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class HistorialRevision {
    private String descripcion;
    private String codigoModerador;
    private LocalDateTime fecha;
    private EstadoNegocio estado;

    public HistorialRevision(LocalDateTime fecha, EstadoNegocio estadoNegocio) {
        this.fecha = fecha;
        this.estado = estadoNegocio;
    }

    public HistorialRevision(String s, String descripcion, String s1, LocalDateTime now, EstadoNegocio estadoNegocio) {
    }
}
