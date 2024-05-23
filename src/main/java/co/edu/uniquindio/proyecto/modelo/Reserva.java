package co.edu.uniquindio.proyecto.modelo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Document("Reservas")

@Getter
@Setter
@ToString
public class Reserva implements Serializable {
    @Id
    private String codigo;
    private String codigoCliente;
    private String codigoNegocio;
    private LocalDateTime fecha;
    private LocalTime hora;
    private double costo;

}
