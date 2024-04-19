package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.SesionDTO;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.EstadoRegistro;
import co.edu.uniquindio.proyecto.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class CuentaServicioImpl implements CuentaServicio {

    private final ClienteRepo clienteRepo;

    EmailService emailService;

    PasswordEncoder passwordEncoder;

    @Override
    public Optional<Cliente> iniciarSesion(SesionDTO sesionDTO) throws Exception {
        Optional<Cliente> cliente = clienteRepo.findByEmail(sesionDTO.email());
        if(cliente.isEmpty() || !passwordEncoder.matches(sesionDTO.password(), cliente.get().getPassword())){
            return Optional.empty();
        }
        return cliente;

    }

    @Override
    public void eliminarCuenta(String idCliente) throws Exception {
        Optional<Cliente> optionalCliente = validarClienteExiste(idCliente);

        //Obtenemos el usuario que se quiere eliminar y le asignamos el estado inactivo
        Cliente cliente = optionalCliente.get();
        if (cliente.getEstado().equals(EstadoRegistro.INACTIVO)){
            throw new Exception("La cuenta ya se encuentra eliminada");
        }
        cliente.setEstado(EstadoRegistro.INACTIVO);
        //Como el objeto usuario ya se encuentra en la BD, actualiza el que ya existe con el nuevo estado
        clienteRepo.save(cliente);

    }

    private Optional<Cliente> validarClienteExiste(String idCliente) throws ResourceNotFoundException {
        //Buscamos el usuario que se quiere manipular
        Optional<Cliente> optionalUsuario = clienteRepo.findById(idCliente);

        //Si no se encontró el usuario, lanzamos una excepción
        if(optionalUsuario.isEmpty()){
            throw new ResourceNotFoundException("Cliente no encontrado.");
        }
        return optionalUsuario;
    }

    @Override
    public void enviarCorreoRecuperacion(EmailDTO emailDTO, String email) throws Exception {
        Cliente cliente = clienteRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("El email no esta asociado a un cliente"));

        emailService.enviarCorreo(emailDTO);
    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Cliente cliente = clienteRepo.findById(cambioPasswordDTO.id())
                .orElseThrow(() -> new ResourceNotFoundException("El id no esta asociado a un cliente"));

        cliente.setPassword(cambioPasswordDTO.passwordNueva());
        clienteRepo.save(cliente);
    }
}
