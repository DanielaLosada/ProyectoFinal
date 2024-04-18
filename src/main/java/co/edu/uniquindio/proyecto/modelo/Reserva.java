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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Reserva implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String codigoCliente;
    private LocalDateTime fecha;
    private LocalTime hora;
    private double costo;

}
