package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Repositorios.ClienteRepo;
import co.edu.uniquindio.proyecto.Repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.SesionDTO;
import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.Cuenta;
import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.proyecto.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final ClienteRepo usuarioRepo;
    private final ModeradorRepo moderadorRepo;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception {
        // Verificar que el DTO de inicio de sesión no sea nulo
        if (loginDTO == null) {
            throw new IllegalArgumentException("El DTO de inicio de sesión no puede ser nulo.");
        }

        // Verificar que el correo no sea nulo o vacío
        String email = loginDTO.email();
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío.");
        }

        // Verificar que la contraseña no sea nula o vacía
        String contrasenia = loginDTO.contrasenia();
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }

        // Buscar al usuario por su correo electrónico
        Optional<Cliente> usuarioOptional = usuarioRepo.findByEmail(email);
        Cliente cliente = usuarioOptional.orElseThrow(() -> new ResourceNotFoundException("El correo no se encuentra registrado."));

        // Verificar la contraseña utilizando BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(contrasenia, cliente.getPassword())) {
            throw new AuthenticationException("La contraseña es incorrecta.");
        }

        // Generar el token JWT con la información del cliente
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", cliente.getNombre());
        map.put("id", cliente.getCodigo());
        String token = jwtUtils.generarToken(cliente.getEmail(), map);

        return new TokenDTO(token);
    }


    @Override
    public TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception {
        // Verificar que el DTO de inicio de sesión no sea nulo
        if (loginDTO == null) {
            throw new IllegalArgumentException("El DTO de inicio de sesión no puede ser nulo.");
        }

        // Verificar que el correo no sea nulo o vacío
        String email = loginDTO.email();
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío.");
        }

        // Verificar que la contraseña no sea nula o vacía
        String contrasenia = loginDTO.contrasenia();
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }

        // Buscar al moderador por su correo electrónico
        Optional<Moderador> usuarioOptional = moderadorRepo.findByEmail(email);
        Moderador moderador = usuarioOptional.orElseThrow(() -> new ResourceNotFoundException("El correo no se encuentra registrado."));

        // Verificar la contraseña utilizando BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(contrasenia, moderador.getPassword())) {
            throw new AuthenticationException("La contraseña es incorrecta.");
        }

        // Generar el token JWT con la información del moderador
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "MODERADOR");
        map.put("nombre", moderador.getNombre());
        map.put("id", moderador.getIdModerador());
        String token = jwtUtils.generarToken(moderador.getEmail(), map);

        return new TokenDTO(token);
    }

}
