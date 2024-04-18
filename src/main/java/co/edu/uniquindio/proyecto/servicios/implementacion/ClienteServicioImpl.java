package co.edu.uniquindio.proyecto.servicios.implementacion;


import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio {
    private final ClienteRepo clienteRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception {
        return null;
    }

    @Override
    public void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) {

    }

    @Override
    public void eliminarCuenta() {

    }

    @Override
    public void cambiarPassword() {

    }

    @Override
    public void enviarLinkRecuperacion() {

    }

    @Override
    public void iniciarSesion() {

    }

    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {

        if( existeEmail(registroClienteDTO.email()) ){
            throw new Exception("El correo ya se encuentra registrado");
        }

        if( existeNickname(registroClienteDTO.nickname()) ){
            throw new Exception("El nickname ya se encuentra registrado por otro usuario");
        }
    //Se crea el objeto Cliente
        Cliente cliente = new Cliente();
    //Se le asignan sus campos
        cliente.setNombre( registroClienteDTO.nombre() );
        cliente.setNickName( registroClienteDTO.nickname() );
        cliente.setCiudad( registroClienteDTO.ciudadResidencia() );
        cliente.setFotoPerfil( registroClienteDTO.fotoPerfil() );
        cliente.setEmail( registroClienteDTO.email() );
        cliente.setPassword( registroClienteDTO.password() );
        cliente.setEstado(EstadoRegistro.ACTIVO);
    //Se guarda en la base de datos y obtenemos el objeto registrado
        Cliente clienteGuardado = clienteRepo.save(cliente);
    //Retornamos el id (código) del cliente registrado
        return clienteGuardado.getCodigo();
    }

    private boolean existeEmail(String email) {
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeNickname (String nickName){
        return clienteRepo.findBynickName(nickName).isPresent();
    }
    @Override
    public void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
    //Buscamos el cliente que se quiere actualizar
        Optional<Cliente> optionalCliente = clienteRepo.findById( actualizarClienteDTO.id() );
    //Si no se encontró el cliente, lanzamos una excepción
        if(optionalCliente.isEmpty()){
            throw new Exception("No se encontró el cliente a actualizar");
        }
    //Obtenemos el cliente que se quiere actualizar y le asignamos los nuevos valores (elnickname no se puede cambiar)
        Cliente cliente = optionalCliente.get();
        cliente.setNombre( actualizarClienteDTO.nombre() );
        cliente.setFotoPerfil( actualizarClienteDTO.fotoPerfil() );
        cliente.setCiudad( actualizarClienteDTO.ciudadResidencia() );
        cliente.setEmail( actualizarClienteDTO.email() );
    //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        clienteRepo.save(cliente);
    }
    @Override
    public void eliminarCliente(String idCuenta) throws Exception {
    //Buscamos el cliente que se quiere eliminar
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCuenta);
    //Si no se encontró el cliente, lanzamos una excepción
        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró el cliente a eliminar");
        }
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado inactivo
        Cliente cliente = optionalCliente.get();
        cliente.setEstado(EstadoRegistro.INACTIVO);
    //Como el objeto cliente ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        clienteRepo.save(cliente);
    }
    @Override
    public DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception {
    //Buscamos el cliente que se quiere eliminar
        Optional<Cliente> optionalCliente = clienteRepo.findById( idCuenta );
    //Si no se encontró el cliente, lanzamos una excepción
        if(optionalCliente.isEmpty()){
            throw new Exception("No se encontró el cliente a con el id "+idCuenta);
        }
    //Obtenemos el cliente
        Cliente cliente = optionalCliente.get();
    //Retornamos el cliente en formato DTO
        return new DetalleClienteDTO(cliente.getCodigo(), cliente.getNombre(),
                cliente.getFotoPerfil(), cliente.getNickName(), cliente.getEmail(), cliente.getCiudad());
    }
    @Override
    public List<ItemClienteDTO> listarClientes() {
    //Obtenemos todos los clientes de la base de datos
        List<Cliente> clientes = clienteRepo.findAll();
    //Creamos una lista de DTOs de clientes
        List<ItemClienteDTO> items = new ArrayList<>();
    //Recorremos la lista de clientes y por cada uno creamos un DTO y lo agregamos a la lista
        for (Cliente cliente : clientes) {
            items.add(new ItemClienteDTO(cliente.getCodigo(), cliente.getNombre(),
                    cliente.getFotoPerfil(), cliente.getNickName(), cliente.getCiudad()));
        }
        return items;
    }

}
