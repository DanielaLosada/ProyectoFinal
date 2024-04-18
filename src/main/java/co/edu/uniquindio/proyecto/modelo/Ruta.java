package co.edu.uniquindio.proyecto.modelo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@ToString



public class Ruta {

    private double distancia;
    private LocalTime duracionEstimada;
    private TipoMedioTransporte MedioTransporte;

}
