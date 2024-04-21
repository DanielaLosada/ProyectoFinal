package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.Repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.Repositorios.ReseniaRepo;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RegistroNegocioDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if(!validarEstadoRegistroCliente(registroNegocioDTO.codigoCliente())){
            throw new Exception("No se pudo crear el negocio, el estado del cliente es inválido");
        }
        negocio.setCodigoCliente(registroNegocioDTO.codigoCliente());
        negocio.setListHorarios(registroNegocioDTO.listHorarios());
        negocio.setTipoNegocio(registroNegocioDTO.tipoNegocio());
        negocio.setListHistorialRevisiones(List.of(new HistorialRevision( LocalDateTime.now(), EstadoNegocio.PENDIENTE)));
        negocio.setEstadoRegistro(EstadoRegistro.ACTIVO);
        negocio.setUbicacion(registroNegocioDTO.ubicacion());

        //Se guarda en la base de datos y obtenemos el objeto registrado
        Negocio negocioGuardado = negocioRepo.save(negocio);
        System.out.println("Negocio creado correctamente");

        //Retornamos el id (código) del negocio registrado en la BD
        return negocioGuardado.getCodigo();
    }

    private boolean validarEstadoRegistroCliente(String idCliente) throws ResourceNotFoundException {
        boolean aux = true;

        if (!clienteRepo.findById(idCliente).isPresent()){
            throw new ResourceNotFoundException("No se pudo encontrar el cliente en la BD");
        }
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        Cliente cliente = optionalCliente.get();

        if (cliente.getEstado().equals(EstadoRegistro.INACTIVO)){
            aux=false;
        }
        return aux;
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {
        Optional<Negocio> optionalNegocio = validarNegocioExiste(actualizarNegocioDTO.id());
        Negocio negocio = optionalNegocio.get();
        negocio.setListHorarios(actualizarNegocioDTO.horarioNegocio());
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());
        negocio.setListImagenes(actualizarNegocioDTO.listImagenes());
        negocio.setListTelefonos(actualizarNegocioDTO.listTelefonos());
        negocioRepo.save(negocio);
        System.out.println("Negocio actualizado correctamente");
    }

    private Optional<Negocio> validarNegocioExiste(String idNegocio) throws ResourceNotFoundException {
        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);
        if(optionalNegocio.isEmpty()){
            throw new ResourceNotFoundException("El negocio no se pudo encontrar");
        }

        return optionalNegocio;
    }

    @Override
    public void eliminarNegocio(String idNegocio) throws Exception {
        Optional<Negocio> optionalNegocio = validarNegocioExiste(idNegocio);

        Negocio negocio = optionalNegocio.get();
        if (negocio.getEstadoRegistro().equals(EstadoRegistro.INACTIVO)){
            throw new Exception("No fue posible eliminar el Negocio.");
        }
        negocio.setEstadoRegistro(EstadoRegistro.INACTIVO);
        negocioRepo.save(negocio);
        System.out.println("El negocio se elimino correctamente");
    }

    @Override
    public DetalleNegocioDTO buscarNegocios(String idNegocio, String idUsuario) throws Exception {
        Optional<Negocio> optionalNegocio = validarNegocioExiste(idNegocio);
        Negocio negocio = optionalNegocio.get();

        //Una vez se valida que el negocio existe lo agregamos a las busquedas del usuario
        //que realiza la busqueda
        agregarRegistroBusquedas(idUsuario,negocio.getNombre());

        return new DetalleNegocioDTO(negocio.getCodigo(),negocio.getNombre(),negocio.getDescripcion(),negocio.getListImagenes(),
                negocio.getListTelefonos(),negocio.getUbicacion(),negocio.getCodigoCliente(),negocio.getListHorarios(),
                negocio.getTipoNegocio(),negocio.getListHistorialRevisiones());
    }

    private void agregarRegistroBusquedas(String idCliente, String nombre) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(idCliente);
        if (clienteOptional.isEmpty()){
            throw new Exception("Error al buscar el usuario con el id "+idCliente);
        }
        Cliente cliente = clienteOptional.get();
        if (cliente.getRegistroBusquedas().isEmpty()){
            cliente.setRegistroBusquedas(new ArrayList<>(List.of(nombre)));
        }else {
            cliente.getRegistroBusquedas().addAll(List.of(nombre));
        }
        clienteRepo.save(cliente);
    }

    @Override
    public List<ItemNegocioDTO> listarNegocioPropietario(String idCliente) throws Exception {
        List<Negocio> listaNegocios = negocioRepo.findNegocioByCodigoCliente(idCliente); //Hacer consulta que traiga todos los negocios del usuario indicado por parámetro

        if (listaNegocios.isEmpty()){
            throw new ResourceNotFoundException("Error al momento de obtener los negocios relacionados al propietario "+idCliente);
        }
        List<ItemNegocioDTO> items = new ArrayList<>();

        for(Negocio negocio : listaNegocios){
            items.add(new ItemNegocioDTO(negocio.getCodigo(),negocio.getNombre(),negocio.getListImagenes(),negocio.getTipoNegocio(),negocio.getUbicacion()));
        }
        return items;
    }

    @Override
    public DetalleNegocioDTO filtarNegocioNombre(String nombreNegocio) throws Exception {
        Optional<Negocio> optionalNegocio = negocioRepo.findByNombre(nombreNegocio);

        if (optionalNegocio.isEmpty()){
            throw new ResourceNotFoundException("El usuario no fue encontrado");
        }
        Negocio negocio = optionalNegocio.get();
        DetalleNegocioDTO detalleNegocioDTO = new DetalleNegocioDTO(negocio.getCodigo(),negocio.getNombre(),
                negocio.getDescripcion(),negocio.getListImagenes(),negocio.getListTelefonos(),
                negocio.getUbicacion(),negocio.getCodigoCliente(),negocio.getListHorarios(),
                negocio.getTipoNegocio(),negocio.getListHistorialRevisiones());
        return detalleNegocioDTO;
    }

    @Override
    public List<ItemNegocioDTO> filtarNegocioTipo(TipoNegocio tipoNegocio) throws Exception {
        List<Negocio> listaNegocios = negocioRepo.buscarNegocioPorTipo(tipoNegocio);

        if (listaNegocios.isEmpty()){
            throw new ResourceNotFoundException("No se pudo encontrar los negocios con tipo "+tipoNegocio);
        }

        List<ItemNegocioDTO> items = new ArrayList<>();

        for(Negocio negocio : listaNegocios){
            items.add(new ItemNegocioDTO(negocio.getCodigo(),negocio.getNombre(),negocio.getListImagenes(),negocio.getTipoNegocio(),negocio.getUbicacion()));
        }
        return items;
    }

    @Override
    public List<ItemNegocioDTO> filtrarNegocioEstado(EstadoNegocio estadoNegocio) throws Exception {
        List<Negocio> listaNegocios = negocioRepo.ListarNegocioEstado(estadoNegocio);
        if (listaNegocios.isEmpty()){
            throw new ResourceNotFoundException("No se pudo encontrar los negocios con estado "+estadoNegocio);
        }
        List<ItemNegocioDTO> items = new ArrayList<>();
        for (Negocio negocio: listaNegocios){
            items.add(new ItemNegocioDTO(negocio.getCodigo(),negocio.getNombre(),negocio.getListImagenes(),negocio.getTipoNegocio(),negocio.getUbicacion()));
        }
        return  items;
    }
}
