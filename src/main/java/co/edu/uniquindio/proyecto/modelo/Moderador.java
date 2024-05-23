package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Document("Moderadores")
public class Moderador extends Cuenta implements Serializable {
    @Id
    private String idModerador;

    @Builder
    public Moderador(String nombre, String email, String contrasenia, EstadoRegistro estadoRegistro) {
        super(nombre, email, contrasenia, estadoRegistro);
    }
}
