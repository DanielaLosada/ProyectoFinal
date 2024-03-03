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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document ("Negocios")

public class  Negocio implements Serializable {

    private String nombre;
    private String descripcion;
    private String codigo;
    private String codigoCliente;
    private List<String> listTelefonos;
    private TipoNegocio tipoNegocio;
    private List <String> listImagenes;
    private List <Horario> listHorarios;
    private EstadoRegistro estadoRegistro;
    private Ubicacion ubicacion;
    private List<HistorialRevision> listHistorialRevisiones;












}
