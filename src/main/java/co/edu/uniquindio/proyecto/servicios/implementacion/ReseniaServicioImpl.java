package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ReseniaRepo;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ItemReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.RegistroReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ResponderReseniaDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
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

    @Override
    public String crearResenia(RegistroReseniaDTO registroReseniaDTO) throws Exception {
        Resenia resenia = new Resenia();
        resenia.setMensaje(registroReseniaDTO.descripcion());
        resenia.setCalificacion(registroReseniaDTO.calificacion());
        resenia.setCodigoCliente(registroReseniaDTO.idUsuario());
        resenia.setCodigoNegocio(registroReseniaDTO.idNegocio());
        resenia.setFecha(LocalDateTime.now());
        Resenia reseniaGuardada= reseniaRepo.save(resenia);

        return reseniaGuardada.getCodigo();
    }

    @Override
    public void responderResenia(ResponderReseniaDTO responderReseniaDTO) throws Exception {

        Optional<Resenia> optionalComentario = reseniaRepo.findById(responderReseniaDTO.idComentario());
        if (optionalComentario.isEmpty()){
            throw new Exception("Error Comentario no encontrado");
        }
        Resenia resenia = optionalComentario.get();
//        if (comentario.getRespuesta()==null || comentario.getRespuesta().isEmpty()){
//            throw new Exception("El comentario solo puede ser respondido una vez.");
//        }
        resenia.setRespuesta(responderReseniaDTO.respuesta());
        reseniaRepo.save(resenia);
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
