package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ItemModeradorDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RevisionNegocioDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


public interface ModeradorServicio {
    ItemModeradorDTO obtenerInfoModerador(String idModerador) throws Exception;
    void aprobarNegocio(RevisionNegocioDTO negocioDTO) throws Exception;
    void rechazarNegocio(RevisionNegocioDTO negocioDTO) throws Exception;
}
