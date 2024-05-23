package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ItemReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.RegistroReservaDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.*;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ClienteServicio {

    //CRUD-----------------------------------------------
    TokenDTO registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;
    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;
    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;
    void eliminarCliente(String idCuenta)throws Exception;

    //----------------------------------------------------
    List<ItemClienteDTO> listarClientes();

    CambioPasswordDTO recuperarContrasenia(String idCliente) throws Exception;

    List<Cliente> getAll();

    String agregarNegocioFavorito(String idCliente, String idNegocio) throws Exception;
    String eliminarNegocioFavorito(String idCliente, String idNegocio) throws ResourceNotFoundException;
    List<Negocio> listarNegociosFavoritos(String idCliente) throws Exception;
    void actualizarUbicacion(String idCliente,Double longitud, Double latitud) throws Exception;
    double solicitarRuta(String idCliente, Ubicacion ubicacionDestino, TipoMedioTransporte medioTransporte) throws ResourceNotFoundException;

    List<ItemNegocioDTO> recomendarLugares(String idCliente) throws Exception;

    String crearReserva (RegistroReservaDTO registroReservaDTO) throws Exception;


    List<ItemReservaDTO> listarReservasXCliente(String idCliente) throws Exception;
}
