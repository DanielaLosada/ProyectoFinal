package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.Repositorios.NegocioRepo;
import co.edu.uniquindio.proyecto.Repositorios.ReservaRepo;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ActualizarReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.ItemReservaDTO;
import co.edu.uniquindio.proyecto.dto.ReservaDTO.RegistroReservaDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.Reserva;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReservaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServicioImpl implements ReservaServicio {

    private final ReservaRepo reservaRepo;
    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;

    @Override
    public void eliminarReserva(String idReserva) throws Exception {
        Optional<Reserva> optionalReserva = validarReservaExiste(idReserva);
        if (optionalReserva.isPresent()) {
            reservaRepo.deleteById(idReserva);
            System.out.println("Reserva eliminada correctamente");
        } else {
            throw new Exception("La reserva con el ID " + idReserva + " no existe");
        }
    }

    private Optional<Reserva> validarReservaExiste(String idReserva) throws ResourceNotFoundException {
        Optional<Reserva> optionalReserva = reservaRepo.findById(idReserva);
        if(optionalReserva.isEmpty()){
            throw new ResourceNotFoundException("Reserva no encontrada.");
        }
        System.out.println("Reserva encontrada correctamente");
        return optionalReserva;
    }

    @Override
    public void actualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception {
        Optional<Reserva> optionalReserva = validarReservaExiste(actualizarReservaDTO.idReserva());
        Reserva reserva = optionalReserva.get();
        reserva.setHora(actualizarReservaDTO.hora());
        if (!esFechaValida(actualizarReservaDTO.fecha())){
            throw new Exception("La hora de la reserva no es válido. Debe estar dentro del horario permitido.");
        }
        reserva.setFecha(actualizarReservaDTO.fecha());
        // Guardar la reserva actualizada en el repositorio
        reservaRepo.save(reserva);
        System.out.println("Reserva actualizada correctamente");
        }


    public boolean esFechaValida(LocalDateTime fecha) {
        LocalDateTime fechaActual = LocalDateTime.now();
        if (fecha.isBefore(fechaActual)) {
            return true;
        }
        return false;
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

    @Override
    public List<ItemReservaDTO> listarReservasXNegocio(String idNegocio) throws Exception {
        List<Reserva> listaReserva = negocioRepo.listarReservaNegocio(idNegocio); //Hacer consulta que traiga todos los negocios del usuario indicado por parámetro

        if (listaReserva.isEmpty()){
            throw new ResourceNotFoundException("Error al momento de obtener las reservas relacionadas al negocio "+idNegocio);
        }
        List<ItemReservaDTO> items = new ArrayList<>();
        for(Reserva reserva : listaReserva){
            items.add(new ItemReservaDTO(reserva.getCodigo(), reserva.getCodigoCliente(), reserva.getCodigoNegocio(),
                    reserva.getFecha(), reserva.getHora()));
        }
        return items;

    }
}
