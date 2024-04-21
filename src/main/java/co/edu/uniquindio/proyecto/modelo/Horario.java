package co.edu.uniquindio.proyecto.modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Horario {

    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String dia;

    public Horario(LocalTime horaInicio, LocalTime horaFin, String dia) {
        this.horaInicio=horaInicio;
        this.horaFin=horaFin;
        this.dia=dia;
    }
}
