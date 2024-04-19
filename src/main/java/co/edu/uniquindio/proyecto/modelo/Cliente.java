package co.edu.uniquindio.proyecto.modelo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Document ("Clientes")
public class Cliente extends Cuenta   implements Serializable {

    private String fotoPerfil;
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nickName;
    private String ciudad;
    private Ubicacion ubicacion;
    private List<String> registroBusquedas;
    private List <String> negociosFavoritos;
























}
