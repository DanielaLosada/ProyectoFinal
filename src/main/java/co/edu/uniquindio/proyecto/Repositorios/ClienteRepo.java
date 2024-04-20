package co.edu.uniquindio.proyecto.Repositorios;

import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.Negocio;
import co.edu.uniquindio.proyecto.modelo.Reserva;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String > {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findBynickName (String nickName);
    Optional<Cliente> findById (String id);

    @Query(value="{ '_id': ?0 }", fields="{ 'listReservas': 1 }")
    List<Reserva> listarReservaCliente(String idCliente);

    @Query(value="{ '_id': ?0 }", fields="{ 'listNegociosFavoritos': 1 }")
    List<Negocio> ListarFavoritos(String idUsuario);

}
