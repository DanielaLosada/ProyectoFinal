package co.edu.uniquindio.proyecto.modelo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString


public class Comentario {

    private int calificacion;
    private String codigoCliente;
    private String codigoNegocio;
    private String codigo;
    private String mensaje;
    private String respuesta;
    private LocalDateTime fecha;

}
