package co.edu.uniquindio.proyecto.Repositorios;

import co.edu.uniquindio.proyecto.modelo.*;
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

    @Query (value = "{'listHistorialRevisiones.estado' : ?0 }")
    List<Negocio> ListarNegocioEstado(EstadoNegocio estadoNegocio);

    List<Negocio> findNegocioByCodigoCliente(String idCliente);

    @Query (value = "{ 'nombre' : { $regex : ?0, $options: 'i' } }" )
    List<Object> busquedaNombresSimilares(String lugar);

    @Query(value="{ '_id': ?0 }", fields="{ 'listReservas': 1 }")
    List<Reserva> listarReservaNegocio(String idNegocio);

}
