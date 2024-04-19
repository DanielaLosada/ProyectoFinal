package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RegistroNegocioDTO;
import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;

import java.util.List;

public interface NegocioServicio {

    //CRUD NEGOCIO----------------------------------------
    String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception;
    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;
    void eliminarNegocio(String idNegocio)throws Exception;
    DetalleNegocioDTO buscarNegocios(String idNegocio, String idUsuario) throws Exception;
    List<ItemNegocioDTO> listarNegocioPropietario(String idUsuario) throws Exception;


    //BUSQUEDAS--------------------------------------------
    DetalleNegocioDTO filtarNegocioNombre(String nombreNegocio) throws Exception;
    List<ItemNegocioDTO> filtarNegocioTipo(TipoNegocio tipoNegocio) throws Exception;
    List<ItemNegocioDTO> filtrarNegocioPorDistancia(String idUsuario,int distanciaAlrededor) throws Exception;
    List<ItemNegocioDTO> filtrarNegocioEstado (EstadoNegocio estadoNegocio) throws Exception;


    //-------------------------------------------------------

}
