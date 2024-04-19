package co.edu.uniquindio.proyecto.Repositorios;

import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.modelo.Negocio;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String > {
    Optional<Negocio> findByNombre(String nombreNegocio);

    @Query (value = "{tipoNegocio :  ?0}")
    List<Negocio> buscarNegocioPorTipo(TipoNegocio tipoNegocio);

    @Query (value = "{'estadoRegistro' :  ?0}")
    List<Negocio> ListarNegocioPorER(EstadoRegistro estadoRegistro);

    @Query (value = "{'historialNegocio.estadoNegocio' : ?0 }")
    List<Negocio> ListarNegocioEstado(EstadoNegocio estadoNegocio);

    @Query (value = "{'idCliente' :  ?0}" )
    List<Negocio> listarNegocioPropietario(String idCliente);

    @Aggregation({
            "{$match: {_id: ?0}}",
            "{$lookup: {from: 'negocio', localField: 'negociosFavoritos', foreignField: '_id', as: 'negocio_favorito'}}",
            "{$unwind: '$negocio_favorito'}",
            "{$project: { _id: 0, negociosFavoritos: '$negocio_favorito'}}"})
    List<Negocio> ListarFavoritos(String idUsuario);
}
