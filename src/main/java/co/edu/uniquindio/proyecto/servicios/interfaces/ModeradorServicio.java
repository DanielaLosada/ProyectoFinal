package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ItemModeradorDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RevisionNegocioDTO;

public interface ModeradorServicio {
    ItemModeradorDTO obtenerDatosModerador(String idModerador) throws Exception;
    void aprobarNegocio(RevisionNegocioDTO negocioDTO) throws Exception;
    void rechazarNegocio(RevisionNegocioDTO negocioDTO) throws Exception;
}
