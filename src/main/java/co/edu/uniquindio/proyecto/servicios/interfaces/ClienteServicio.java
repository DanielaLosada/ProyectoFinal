package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;

public interface ClienteServicio {

    String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception;

    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO);

    void eliminarCuenta();

    void cambiarPassword();

    void enviarLinkRecuperacion();

    void iniciarSesion();


}
