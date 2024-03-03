package co.edu.uniquindio.proyecto.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document("Cuentas")

public class Cuenta {

    private String nombre;
    private String password;
    private String email;
    private EstadoRegistro estado;



}
