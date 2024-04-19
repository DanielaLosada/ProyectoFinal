package co.edu.uniquindio.proyecto.Repositorios;

import co.edu.uniquindio.proyecto.modelo.Cuenta;
import co.edu.uniquindio.proyecto.modelo.Moderador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ModeradorRepo extends MongoRepository<Moderador,String> {
    Optional<Moderador> findByIdModerador(String idModerador);
    Optional<Cuenta> findByCorreo(String correo);
}
