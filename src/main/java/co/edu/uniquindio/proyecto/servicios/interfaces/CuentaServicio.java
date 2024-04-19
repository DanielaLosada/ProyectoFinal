package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.SesionDTO;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;

import java.util.Optional;

public interface CuentaServicio {
    Optional<Cliente> iniciarSesion(SesionDTO sesionDTO)throws Exception;
    void eliminarCuenta(String idCuenta)throws Exception;
    void enviarCorreoRecuperacion(EmailDTO emailDTO, String email)throws Exception;
    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;
}