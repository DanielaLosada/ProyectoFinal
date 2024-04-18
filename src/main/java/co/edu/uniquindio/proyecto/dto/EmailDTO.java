package co.edu.uniquindio.proyecto.dto;

import org.hibernate.validator.constraints.Length;

public record EmailDTO(@Length(max = 20) String asunto,
                       @Length(max = 300) String cuerpo,
                       @Length(max = 40) String destinatario) {

}
