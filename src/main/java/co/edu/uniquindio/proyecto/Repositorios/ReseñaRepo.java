package co.edu.uniquindio.proyecto.Repositorios;

import co.edu.uniquindio.proyecto.modelo.Reseña;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseñaRepo extends MongoRepository<Reseña, String > {


}
