package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.RegistroClienteDTO;

import java.util.List;

public interface ClienteServicio {

    String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception;

    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO);

    void eliminarCuenta();

    void cambiarPassword();

    void enviarLinkRecuperacion();

    void iniciarSesion();

    String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;
    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;
    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;
    void eliminarCliente(String idCuenta)throws Exception;
    List<ItemClienteDTO> listarClientes();

}
