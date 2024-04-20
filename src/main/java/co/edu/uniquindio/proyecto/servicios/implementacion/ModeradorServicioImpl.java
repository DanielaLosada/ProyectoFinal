package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.Repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.dto.ItemModeradorDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RevisionNegocioDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.EstadoNegocio;
import co.edu.uniquindio.proyecto.modelo.HistorialRevision;
import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.modelo.Negocio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;
    private final NegocioRepo negocioRepo;

    @Override
    public ItemModeradorDTO obtenerInfoModerador(String idModerador) throws Exception {
        Optional<Moderador> optionalModerador = moderadorRepo.findByIdModerador(idModerador);
        if (optionalModerador.isEmpty()){
            throw new Exception("Error al momento de obtener el moderador "+idModerador);
        }
        Moderador moderador= optionalModerador.get();

        return new ItemModeradorDTO(moderador.getIdModerador() ,moderador.getNombre());
    }

    @Override
    public void aprobarNegocio(RevisionNegocioDTO revisionDTO) throws Exception {
        Optional<Negocio> optionalNegocio = validarNegocioExiste(revisionDTO.idNegocio());
        Negocio negocio = optionalNegocio.get();

        if (negocio.getEstadoRegistro().equals(EstadoNegocio.RECHAZADO)){
            throw new Exception("No fue posible aprobar el Negocio. Verifica su estado previo.");
        }

        negocio.getListHistorialRevisiones().add( new HistorialRevision(
                revisionDTO.idNegocio(),
                revisionDTO.descripcion(),
                revisionDTO.codigoModerador(),
                LocalDateTime.now(),
                EstadoNegocio.APROBADO
        ) );
        negocioRepo.save(negocio);
    }

    @Override
    public void rechazarNegocio(RevisionNegocioDTO revisionDTO) throws Exception {

        Optional<Negocio> optionalNegocio = validarNegocioExiste(revisionDTO.idNegocio());

        Negocio negocio = optionalNegocio.get();
        if (negocio.getEstadoRegistro().equals(EstadoNegocio.RECHAZADO)){
            throw new Exception("No fue posible rechazar el Negocio. Verifica su estado previo.");
        }
        negocio.getListHistorialRevisiones().add( new HistorialRevision(
                revisionDTO.idNegocio(),
                revisionDTO.descripcion(),
                revisionDTO.codigoModerador(),
                LocalDateTime.now(),
                EstadoNegocio.APROBADO
        ) );
        negocioRepo.save(negocio);
    }

    private Optional<Negocio> validarNegocioExiste(String idNegocio) throws ResourceNotFoundException {

        //Buscamos el negocio que se quiere manipular
        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);

        //Si no se encontró el negocio, lanzamos una excepción
        if(optionalNegocio.isEmpty()){
            throw new ResourceNotFoundException("Negocio no encontrado.");
        }
        return optionalNegocio;
    }






}
