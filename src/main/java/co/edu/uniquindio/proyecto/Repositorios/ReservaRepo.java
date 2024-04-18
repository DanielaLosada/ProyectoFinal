package co.edu.uniquindio.proyecto.Repositorios;

import co.edu.uniquindio.proyecto.modelo.Negocio;
import co.edu.uniquindio.proyecto.modelo.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepo  extends MongoRepository<Reserva, String > {

}
