package co.edu.uniquindio.proyecto.Repositorios;

import co.edu.uniquindio.proyecto.modelo.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String > {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findBynickName (String nickName);
    Optional<Cliente> findById (String id);
}
