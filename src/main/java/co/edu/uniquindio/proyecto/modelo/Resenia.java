package co.edu.uniquindio.proyecto.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString

@Document("resenia")
public class Resenia implements Serializable {

    private int calificacion;
    private String codigoCliente;
    private String codigoNegocio;
    private String codigo;
    private String mensaje;
    private String respuesta;
    private LocalDateTime fecha;

}
