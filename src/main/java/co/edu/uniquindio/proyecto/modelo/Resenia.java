package co.edu.uniquindio.proyecto.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString


public class Resenia {

    private int calificacion;
    private String codigoCliente;
    private String codigoNegocio;
    private String codigo;
    private String mensaje;
    private String respuesta;
    private LocalDateTime fecha;

}
