package co.edu.uniquindio.proyecto.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class Ubicacion {

    private double  latitud;
    private double  longitud;
    private List<Ruta> listRutas;

}
