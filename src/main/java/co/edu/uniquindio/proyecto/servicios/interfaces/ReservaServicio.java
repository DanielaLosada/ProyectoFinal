package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ActualizarReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ItemReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.RegistroReservaDTO;

import java.util.List;

public interface ReservaServicio {
    void eliminarReserva(String idReserva) throws Exception;

    void actualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception;

    List<ItemReservaDTO> listarReservasXCliente(String idCliente) throws Exception;
    List<ItemReservaDTO> listarReservasXNegocio(String idNegocio) throws Exception;
}
