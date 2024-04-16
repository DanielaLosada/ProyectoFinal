package co.edu.uniquindio.proyecto.Repositorios;

import co.edu.uniquindio.proyecto.modelo.Cliente;

import java.util.Optional;

public interface ClienteRepo {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findBynickName (String nickName);

    Optional<Cliente> findById (String id);
}
