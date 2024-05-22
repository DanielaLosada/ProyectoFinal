package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.Repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.Repositorios.ReseniaRepo;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ItemReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.RegistroReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ResponderReseniaDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.Negocio;
import co.edu.uniquindio.proyecto.modelo.Resenia;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReseniaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReseniaServicioImpl implements ReseniaServicio {
    private final ReseniaRepo reseniaRepo;
    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;
    private final  EmailServicioImpl emailServicio;

    @Override
    public String crearResenia(RegistroReseniaDTO registroReseniaDTO) throws Exception {
        // Verificar que el DTO de registro de reseña no sea nulo
        if (registroReseniaDTO == null) {
            throw new IllegalArgumentException("El DTO de registro de reseña no puede ser nulo.");
        }

        // Verificar que la descripción de la reseña no sea nula o vacía
        String descripcion = registroReseniaDTO.descripcion();
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción de la reseña no puede ser nula o vacía.");
        }

        // Verificar que la calificación de la reseña sea un valor válido
        int calificacion = registroReseniaDTO.calificacion();
        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación de la reseña debe estar entre 1 y 5.");
        }

        // Verificar que el ID del usuario y del negocio no sean nulos o vacíos
        String idUsuario = registroReseniaDTO.idUsuario();
        if (idUsuario == null || idUsuario.isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo o vacío.");
        }
        String idNegocio = registroReseniaDTO.idNegocio();
        if (idNegocio == null || idNegocio.isEmpty()) {
            throw new IllegalArgumentException("El ID del negocio no puede ser nulo o vacío.");
        }

        // Verificar si el cliente existe
        Cliente cliente = clienteRepo.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ID " + idUsuario + " no existe en la base de datos."));



        // Verificar si el negocio existe
        Negocio negocio = negocioRepo.findById(idNegocio)
                .orElseThrow(() -> new ResourceNotFoundException("El negocio con ID " + idNegocio + " no existe en la base de datos."));

        Cliente owner = clienteRepo.findById(negocio.getCodigoCliente())
                .orElseThrow(() -> new ResourceNotFoundException("El owner con ID " + negocio.getCodigoCliente() + " no existe en la base de datos."));

        // Crear la reseña
        Resenia resenia = new Resenia();
        resenia.setMensaje(descripcion);
        resenia.setCalificacion(calificacion);
        resenia.setCodigoCliente(idUsuario);
        resenia.setCodigoNegocio(idNegocio);
        resenia.setFecha(LocalDateTime.now());

        // Guardar la reseña en la base de datos
        Resenia reseniaGuardada = reseniaRepo.save(resenia);

        emailServicio.enviarCorreo(new EmailDTO("Nuevo Comentario", cliente.getNombre() + " hizo una " +
                "resenia en " + negocio.getNombre() + " hechale un vistazo", owner.getEmail()));

        System.out.println("Reseña creada correctamente");
        return reseniaGuardada.getCodigo();
    }


    @Override
    public void responderResenia(ResponderReseniaDTO responderReseniaDTO) throws Exception {
        // Verificar que el DTO de respuesta de reseña no sea nulo
        if (responderReseniaDTO == null) {
            throw new IllegalArgumentException("El DTO de respuesta de reseña no puede ser nulo.");
        }

        // Verificar que el ID de la reseña no sea nulo o vacío
        String idComentario = responderReseniaDTO.idComentario();
        if (idComentario == null || idComentario.isEmpty()) {
            throw new IllegalArgumentException("El ID de la reseña no puede ser nulo o vacío.");
        }

        // Verificar que el texto de respuesta no sea nulo o vacío
        String respuesta = responderReseniaDTO.respuesta();
        if (respuesta == null || respuesta.isEmpty()) {
            throw new IllegalArgumentException("El texto de respuesta no puede ser nulo o vacío.");
        }

        // Verificar si la reseña existe
        Resenia resenia = reseniaRepo.findById(idComentario)
                .orElseThrow(() -> new ResourceNotFoundException("La reseña con ID " + idComentario + " no se encuentra en la base de datos."));

        Cliente owner = clienteRepo.findById(resenia.getCodigoCliente())
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ID " + resenia.getCodigoCliente() + " no existe en la base de datos."));

        // Establecer la respuesta en la reseña
        resenia.setRespuesta(respuesta);

        // Guardar los cambios en la base de datos
        reseniaRepo.save(resenia);

        emailServicio.enviarCorreo(new EmailDTO("Nueva respuesta a tu resenia", "Mira la nueva respuesta en tu resenia", owner.getEmail()));

        System.out.println("Se ha respondido a la reseña");
    }


    @Override
    public List<ItemReseniaDTO> listarResenias() {
        //Obtenemos todos los clientes de la base de datos
        List<Resenia> resenias = reseniaRepo.findAll();
        //Creamos una lista de DTOs de clientes
        List<ItemReseniaDTO> items = new ArrayList<>();
        //Recorremos la lista de clientes y por cada uno creamos un DTO y lo agregamos a la lista
        for (Resenia resenia : resenias) {
            items.add(new ItemReseniaDTO(resenia.getCodigo(), resenia.getCodigoCliente(),
                    resenia.getCodigoNegocio() ,resenia.getCalificacion(), resenia.getMensaje(), resenia.getFecha().toString()));
        }
        return items;
    }

}
