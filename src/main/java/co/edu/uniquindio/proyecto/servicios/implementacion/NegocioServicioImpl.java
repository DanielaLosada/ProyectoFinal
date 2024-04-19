package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.Repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.Repositorios.ReseniaRepo;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RegistroNegocioDTO;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NegocioServicioImpl implements NegocioServicio {

    private final NegocioRepo negocioRepo;
    private final ClienteRepo clienteRepo;
    private final ReseniaRepo reseniaRepo;
    @Override
    public String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception {
        //Se crea un objeto negocio que va a contener todos los datos de registroNegocioDTO
        Negocio negocio = new Negocio();
        negocio.setNombre(registroNegocioDTO.nombre());
        negocio.setDescripcion(registroNegocioDTO.descripcion());
        negocio.setListImagenes(registroNegocioDTO.listImagenes());
        negocio.setListTelefonos(registroNegocioDTO.listTelefonos());
        negocio.setUbicacion(registroNegocioDTO.ubicacion());
        if(!validarEstadoRegistroUsuario(registroNegocioDTO.idCliente())){
            throw new Exception("No se pudo crear el negocio, el estado del cliente es inválido");
        }

        negocio.setCodigoCliente(registroNegocioDTO.idCliente());
        if (!esHorarioValido(registroNegocioDTO.listHorarios())) {
            throw new Exception("El horario del negocio no es válido. Debe estar entre las 7:00 am y las 10:00 pm.");
        }
        negocio.setListHorarios(registroNegocioDTO.listHorarios());
        negocio.setTipoNegocio(registroNegocioDTO.tipoNegocio());
        negocio.setListHistorialRevisiones(new HistorialNegocio( LocalDateTime.now(), EstadoNegocio.EN_ESPERA) )
        negocio.setEstadoRegistro(EstadoRegistro.ACTIVO);
        negocio.setUbicacion(registroNegocioDTO.ubicacion());

        //Se guarda en la base de datos y obtenemos el objeto registrado
        Negocio negocioGuardado = negocioRepo.save(negocio);

        //Retornamos el id (código) del negocio registrado en la BD
        return negocioGuardado.getCodigo();
    }

    private boolean esHorarioValido(List<Horario> horarios) {
    }

    private boolean validarEstadoRegistroUsuario(String s) {
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

    }

    @Override
    public void eliminarNegocio(String idNegocio) throws Exception {

    }

    @Override
    public DetalleNegocioDTO buscarNegocios(String idNegocio, String idUsuario) throws Exception {
        return null;
    }

    @Override
    public List<ItemNegocioDTO> listarNegocioPropietario(String idUsuario) throws Exception {
        return null;
    }

    @Override
    public DetalleNegocioDTO filtarNegocioNombre(String nombreNegocio) throws Exception {
        return null;
    }

    @Override
    public List<ItemNegocioDTO> filtarNegocioTipo(TipoNegocio tipoNegocio) throws Exception {
        return null;
    }

    @Override
    public List<ItemNegocioDTO> filtrarNegocioPorDistancia(String idUsuario, int distanciaAlrededor) throws Exception {
        return null;
    }

    @Override
    public List<ItemNegocioDTO> filtrarNegocioEstado(EstadoNegocio estadoNegocio) throws Exception {
        return null;
    }
}
