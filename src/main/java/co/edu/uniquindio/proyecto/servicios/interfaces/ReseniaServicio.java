package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ItemReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.RegistroReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ResponderReseniaDTO;

import java.util.List;

public interface ReseniaServicio {
    String crearResenia(RegistroReseniaDTO registroReseniaDTO) throws Exception;

    void responderResenia(ResponderReseniaDTO responderReseniaDTO) throws Exception;
    List<ItemReseniaDTO> listarResenias() throws Exception;

}
