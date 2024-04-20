package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
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


