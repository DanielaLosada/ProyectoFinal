package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ReseniaDTO.RegistroReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ReseniaDTO;

import java.util.List;

public interface ReseniaServicio {
    String crearResenia(RegistroReseniaDTO registroReseniaDTO) throws Exception;
    List<ReseniaDTO> listarComentarios() throws Exception;

}
