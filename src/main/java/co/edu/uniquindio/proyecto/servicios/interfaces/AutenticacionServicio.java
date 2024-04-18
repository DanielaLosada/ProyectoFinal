package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ClienteDTO.SesionDTO;
import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;

public interface AutenticacionServicio {
    TokenDTO iniciarSesionCliente (LoginDTO sesionDTO) throws Exception;
    TokenDTO iniciarSesionModerador (LoginDTO sesionDTO) throws Exception;
}
