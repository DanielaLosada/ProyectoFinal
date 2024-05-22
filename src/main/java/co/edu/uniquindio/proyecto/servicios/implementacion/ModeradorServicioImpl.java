package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.Repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.Repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.ItemModeradorDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RevisionNegocioDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.*;
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
    private final EmailServicioImpl emailServicio;
    private final ClienteRepo clienteRepo;

    @Override
    public ItemModeradorDTO obtenerInfoModerador(String idModerador) throws Exception {
        Optional<Moderador> optionalModerador = moderadorRepo.findById(idModerador);
        if (optionalModerador.isEmpty()){
            throw new Exception("Error al momento de obtener el moderador "+idModerador);
        }
        Moderador moderador= optionalModerador.get();
        return new ItemModeradorDTO(moderador.getIdModerador() ,moderador.getNombre());
    }

    @Override
    public void aprobarNegocio(RevisionNegocioDTO revisionDTO) throws Exception {
        if (revisionDTO == null) {
            throw new IllegalArgumentException("El DTO de revisión no puede ser nulo.");
        }

        Optional<Negocio> optionalNegocio;
        try {
            optionalNegocio = validarNegocioExiste(revisionDTO.idNegocio());
        } catch (Exception e) {
            throw new Exception("Error al validar la existencia del negocio: " + e.getMessage(), e);
        }

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con ID " + revisionDTO.idNegocio());
        }

        Negocio negocio = optionalNegocio.get();

        Optional<Cliente> optionalCliente;
        try {
            optionalCliente = clienteRepo.findById(negocio.getCodigoCliente());
        } catch (Exception e) {
            throw new Exception("Error al buscar el cliente: " + e.getMessage(), e);
        }

        if (optionalCliente.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el cliente con código " + negocio.getCodigoCliente());
        }

        if (negocio.getEstadoRegistro().equals(EstadoNegocio.RECHAZADO)) {
            throw new Exception("No fue posible aprobar el negocio. Verifica su estado previo.");
        }

        negocio.getListHistorialRevisiones().add(new HistorialRevision(
                revisionDTO.idNegocio(),
                revisionDTO.descripcion(),
                revisionDTO.codigoModerador(),
                LocalDateTime.now(),
                EstadoNegocio.APROBADO
        ));

        try {
            negocioRepo.save(negocio);
        } catch (Exception e) {
            throw new Exception("Error al guardar el negocio: " + e.getMessage(), e);
        }

        try {
            emailServicio.enviarCorreo(new EmailDTO(
                    "Negocio aprobado",
                    "Nos alegra informarle que su negocio cumple con las normas de UniLocal y ha sido aprobado, " +
                            "ya puede ser visualizado por los demás usuarios.",
                    optionalCliente.get().getEmail()
            ));
        } catch (Exception e) {
            throw new Exception("Error al enviar el correo electrónico: " + e.getMessage(), e);
        }

        System.out.println("Se ha aprobado el negocio");
    }


    @Override
    public void rechazarNegocio(RevisionNegocioDTO revisionDTO) throws Exception {
        if (revisionDTO == null) {
            throw new IllegalArgumentException("El DTO de revisión no puede ser nulo.");
        }

        Optional<Negocio> optionalNegocio;
        try {
            optionalNegocio = validarNegocioExiste(revisionDTO.idNegocio());
        } catch (Exception e) {
            throw new Exception("Error al validar la existencia del negocio: " + e.getMessage(), e);
        }

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el negocio con ID " + revisionDTO.idNegocio());
        }

        Negocio negocio = optionalNegocio.get();

        Optional<Cliente> optionalCliente;
        try {
            optionalCliente = clienteRepo.findById(negocio.getCodigoCliente());
        } catch (Exception e) {
            throw new Exception("Error al buscar el cliente: " + e.getMessage(), e);
        }

        if (optionalCliente.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el cliente con código " + negocio.getCodigoCliente());
        }

        if (negocio.getEstadoRegistro().equals(EstadoNegocio.RECHAZADO)) {
            throw new Exception("No fue posible rechazar el negocio. Verifica su estado previo.");
        }

        negocio.getListHistorialRevisiones().add(new HistorialRevision(
                revisionDTO.idNegocio(),
                revisionDTO.descripcion(),
                revisionDTO.codigoModerador(),
                LocalDateTime.now(),
                EstadoNegocio.RECHAZADO
        ));

        try {
            negocioRepo.save(negocio);
        } catch (Exception e) {
            throw new Exception("Error al guardar el negocio: " + e.getMessage(), e);
        }

        try {
            emailServicio.enviarCorreo(new EmailDTO(
                    "Negocio rechazado",
                    "Lamentamos informarle que su negocio ha sido rechazado, sin embargo, puede hacer las " +
                            "correcciones pertinentes para una nueva revisión.",
                    optionalCliente.get().getEmail()
            ));
        } catch (Exception e) {
            throw new Exception("Error al enviar el correo electrónico: " + e.getMessage(), e);
        }

        System.out.println("Se ha rechazado el negocio");
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
