package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ClienteServicio {

    //CRUD-----------------------------------------------
    String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;
    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;
    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;
    void eliminarCliente(String idCuenta)throws Exception;

    //----------------------------------------------------

    String agregarNegocioFavorito(String idCliente, String idNegocio) throws Exception;
    String eliminarNegocioFavorito(String idCliente, String idNegocio) throws ResourceNotFoundException;
    List<ItemNegocioDTO> listarNegociosFavoritos(String idCliente) throws Exception;




}
