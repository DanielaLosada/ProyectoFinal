package co.edu.uniquindio.proyecto.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class HistorialRevision {

    private String idNegocio;
    private String descripcion;
    private String codigoModerador;
    private LocalDateTime fecha;
    private EstadoNegocio estado;


}
