package co.edu.uniquindio.proyecto.servicios.implementacion;


import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.Repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.Repositorios.ReservaRepo;
import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ItemReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.RegistroReservaDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
@Getter
@Setter
public class ClienteServicioImpl implements ClienteServicio {
    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;
    private final ReservaRepo reservaRepo;
    private final EmailService emailService;

    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {
        //Se crea el objeto usuario
        Cliente cliente = new Cliente();
        //Se le asigna al usuario la información que trae registroDTO
        cliente.setNombre( registroClienteDTO.nombre() );
        if (existeNickname(registroClienteDTO.nickname()) ){
            throw new Exception("El nickname ya se encuentra en uso");
        }else
            cliente.setNickName( registroClienteDTO.nickname() );
        cliente.setCiudad( registroClienteDTO.ciudadResidencia() );
        cliente.setFotoPerfil( registroClienteDTO.fotoPerfil() );

        if(existeEmail(registroClienteDTO.email()) ){
            throw new Exception("El correo ya se encuentra registrado");
        }else {
            cliente.setEmail( registroClienteDTO.email() );
        }
        if (!validarContrasenia(registroClienteDTO.password())){
            throw new Exception("La contraseña no cumple con las características indicadas");
        }else {
            cliente.setPassword(registroClienteDTO.password());
        }
        //Encriptación
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( registroClienteDTO.password());
        cliente.setPassword( passwordEncriptada );
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

    private boolean validarContrasenia(String contrasenia) {
        String patron = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(patron, contrasenia);
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
                cliente.getFotoPerfil(), cliente.getNickName(), cliente.getEmail(), cliente.getCiudad(), cliente.getUbicacion());
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

    @Override
    public String agregarNegocioFavorito(String idCliente, String idNegocio) throws Exception {
        // Verificar que el negocio exista en la base de datos
        Negocio negocio = negocioRepo.findById(idNegocio)
                .orElseThrow(() -> new ResourceNotFoundException("El negocio que desea agregar a la lista de favoritos no se encuentra registrado en la base de datos."));
        // Verificar si el usuario existe
        Cliente cliente = clienteRepo.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente no existe."));
        // Inicializar la lista de favoritos si es nula
        if (cliente.getNegociosFavoritos() == null) {
            cliente.setNegociosFavoritos(new ArrayList<>());
        }
        // Inicializar la lista de registros de búsqueda si es nula
        if (cliente.getRegistroBusquedas() == null) {
            cliente.setRegistroBusquedas(new ArrayList<>());
        }
        // Agregar el negocio a la lista de favoritos
        cliente.getNegociosFavoritos().add(obtenerNombreNegocioById(idNegocio));
        // Agregar el nombre del negocio a la lista de registros de búsqueda
        cliente.getRegistroBusquedas().add(obtenerNombreNegocioById(idNegocio));
        // Guardar los cambios en la base de datos
        clienteRepo.save(cliente);
        return idCliente;
    }

    public String crearReserva(RegistroReservaDTO registroReservaDTO) throws Exception {
        Reserva reserva = new Reserva();
        reserva.setCodigoCliente(registroReservaDTO.codigoCliente());
        reserva.setCodigoNegocio(registroReservaDTO.codigoNegocio());
        reserva.setHora(registroReservaDTO.hora());
        reserva.setCosto(registroReservaDTO.costo());
        reserva.setFecha(registroReservaDTO.fecha());
        Cliente cliente = clienteRepo.findById(registroReservaDTO.codigoCliente())
                .orElseThrow(() -> new ResourceNotFoundException("El cliente no existe."));
        if (cliente.getReservas() == null) {
            cliente.setReservas(new ArrayList<>());
        }
        cliente.getReservas().add(reserva);
        clienteRepo.save(cliente);
        Negocio negocio= negocioRepo.findById(registroReservaDTO.codigoNegocio())
                .orElseThrow(() -> new ResourceNotFoundException("El negocio no existe."));
        if (negocio.getListReservas() == null) {
            negocio.setListReservas(new ArrayList<>());
        }
        negocio.getListReservas().add(reserva);
        negocioRepo.save(negocio);
        Reserva reservaGuardado = reservaRepo.save(reserva);
        return reservaGuardado.getCodigo();
    }

    private String obtenerNombreNegocioById(String idNegocio) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepo.findById(idNegocio);
        if (negocioOptional.isEmpty()){
            throw new Exception("Error al obtener el negocio con el id "+idNegocio);
        }
        Negocio negocio = negocioOptional.get();
        return negocio.getNombre();
    }

    @Override
    public String eliminarNegocioFavorito(String idCliente, String idNegocio) throws ResourceNotFoundException {
        if (!negocioRepo.existsById(idNegocio)){
            throw new ResourceNotFoundException("El negocio que desea eliminar de la lista de favoritos no se encuentra registrado en la base de datos.");
        }
        Optional<Cliente> optionalCliente = validarUsuarioExiste(idCliente);
        Cliente cliente = optionalCliente.get();
        cliente.getNegociosFavoritos().remove(idNegocio);
        clienteRepo.save(cliente);
        return idNegocio;
    }

    private Optional<Cliente> validarUsuarioExiste(String idCliente) throws ResourceNotFoundException{
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);
        if(optionalCliente.isEmpty()){
            throw new ResourceNotFoundException("Se encontro una ruta correctamente ");
        }
        return optionalCliente;
    }

    @Override
    public CambioPasswordDTO recuperarContrasenia(String idCliente) throws Exception {
        Optional<Cliente> optionalCliente = validarUsuarioExiste(idCliente);
        String nuevaContra=generarNuevaContrasenia();

        //Obtenemos el usuario que se quiere recuperar la contraseña y verifica su estado
        Cliente cliente = optionalCliente.get();
        if (cliente.getEstado().equals(EstadoRegistro.INACTIVO)){
            throw new Exception("CUENTA CON ESTADO INVÁLIDO");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        cliente.setPassword(passwordEncoder.encode(nuevaContra));
        clienteRepo.save(cliente);
        CambioPasswordDTO cambiarPasswordDTO = new CambioPasswordDTO(idCliente,nuevaContra,cliente.getEmail());
        enviarCorreoRecuperacion(cambiarPasswordDTO);
        return cambiarPasswordDTO;
    }

    private String generarNuevaContrasenia() {
       String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$!%*?&";
        SecureRandom RANDOM = new SecureRandom();
            StringBuilder contrasenia = new StringBuilder();

            for (int i = 0; i < 8; i++) {
                contrasenia.append(CARACTERES_PERMITIDOS.charAt(RANDOM.nextInt(CARACTERES_PERMITIDOS.length())));
            }

            return contrasenia.toString();
    }

    private void enviarCorreoRecuperacion(CambioPasswordDTO cambiarPasswordDTO) throws Exception {

        emailService.enviarCorreo(new EmailDTO("RECUPERACIÓN DE CONTRASEÑA", "Su nueva contraseña es: "+cambiarPasswordDTO.passwordNueva(), cambiarPasswordDTO.email()));
    }

    @Override
    public List<Negocio> listarNegociosFavoritos(String idCliente) throws Exception{

        List<Negocio> listaNegocios = clienteRepo.ListarFavoritos(idCliente);
        if (listaNegocios.isEmpty()){
            throw new ResourceNotFoundException("Error al momento de obtener los negocios favoritos ");
        }
        //List<ItemNegocioDTO> items = new ArrayList<>();
        //for (Negocio negocio: listaNegocios){
         //   items.add(new ItemNegocioDTO(negocio.getCodigo(),negocio.getNombre(),negocio.getListImagenes(),negocio.getTipoNegocio(),negocio.getUbicacion()));
        //}
        return  listaNegocios;
    }

    private Cliente validarUsuarioExistente(String idUsuario) throws ResourceNotFoundException {
        return clienteRepo.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID: " + idUsuario + " no existe."));
    }


    @Override
    public void actualizarUbicacion(String idCliente, Double longitud, Double latitud) throws Exception {
        Optional<Cliente> optionalUsuario = validarUsuarioExiste(idCliente);
        Cliente cliente = optionalUsuario.get();
        cliente.setUbicacion(new Ubicacion(latitud,longitud));
        clienteRepo.save(cliente);
    }

    @Override
    public double solicitarRuta(String idCliente, Ubicacion ubicacionDestino, TipoMedioTransporte medioTransporte) throws ResourceNotFoundException {
        Optional<Cliente> optionalCliente = validarUsuarioExiste(idCliente);
        Cliente cliente = optionalCliente.get();
        Double distancia = calcularDistancia(cliente.getUbicacion().getLatitud(),
                cliente.getUbicacion().getLongitud(),ubicacionDestino.getLatitud(),
                ubicacionDestino.getLongitud());
        return distancia;
    }

    public static Double calcularDistancia(Double latitudUsuario, Double longitudUsuario, Double latitud, Double longitud) {
        final int RADIO_TIERRA = 6371; // Radio de la Tierra en kilómetros
        // Convertir las coordenadas de grados a radianes
        Double latitudUsuarioRad = Math.toRadians(latitudUsuario);
        Double longitudUsuarioRad = Math.toRadians(longitudUsuario);
        Double latitudNegocioRad = Math.toRadians(latitud);
        Double longitudNegocioRad = Math.toRadians(longitud);
        // Calcular las diferencias de latitud y longitud
        Double diferenciaLatitud = latitudNegocioRad - latitudUsuarioRad;
        Double diferenciaLongitud = longitudNegocioRad - longitudUsuarioRad;
        // Calcular la distancia utilizando la fórmula de la distancia haversine
        Double a = Math.pow(Math.sin(diferenciaLatitud / 2), 2) +
                Math.cos(latitudUsuarioRad) * Math.cos(latitudNegocioRad) *
                        Math.pow(Math.sin(diferenciaLongitud / 2), 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distancia = RADIO_TIERRA * c;
        return distancia;
    }

    @Override
    public List<ItemNegocioDTO> recomendarLugares(String idCliente) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(idCliente);
        if (clienteOptional.isEmpty()) {
            throw new Exception("Error al buscar el usuario con el id " + idCliente);
        }
        Cliente cliente = clienteOptional.get();
        //Obtenemos las busquedas del usuario
        List<String> listaLugares = cliente.getRegistroBusquedas();
        return obtenerLugaresRecomendados(listaLugares);
    }

    private List<ItemNegocioDTO> obtenerLugaresRecomendados(List<String> listaLugares) throws ResourceNotFoundException {
        List<Negocio> listaNegocios = new ArrayList<>();
        for (String lugar: listaLugares) {
            if (!listaNegocios.contains(negocioRepo.busquedaNombresSimilares(lugar).get(0))) {
                listaNegocios.add((Negocio) negocioRepo.busquedaNombresSimilares(lugar).get(0));}
        }
        List<ItemNegocioDTO> itemNegocioDTOList = new ArrayList<>();
        for (Negocio negocio : listaNegocios) {
            itemNegocioDTOList.add(new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(), negocio.getListImagenes(), negocio.getTipoNegocio(), negocio.getUbicacion()));
        }
        return itemNegocioDTOList;
    }

    @Override
    public List<ItemReservaDTO> listarReservasXCliente(String idCliente) throws Exception {
        List<Reserva> listaReserva = clienteRepo.listarReservaCliente(idCliente); //Hacer consulta que traiga todos los negocios del usuario indicado por parámetro

        if (listaReserva.isEmpty()){
            throw new ResourceNotFoundException("Error al momento de obtener las reservas relacionadas al cliente "+idCliente);
        }

        List<ItemReservaDTO> items = new ArrayList<>();

        for(Reserva reserva : listaReserva){
            items.add(new ItemReservaDTO(reserva.getCodigo(), reserva.getCodigoCliente(), reserva.getCodigoNegocio(),
                    reserva.getFecha(), reserva.getHora()));
        }
        return items;
    }


}
