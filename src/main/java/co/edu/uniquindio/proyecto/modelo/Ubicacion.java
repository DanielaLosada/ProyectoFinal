package co.edu.uniquindio.proyecto.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class Ubicacion {

    private Double  latitud;
    private Double  longitud;
    private List<Ruta> listRutas;


    public Ubicacion(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
