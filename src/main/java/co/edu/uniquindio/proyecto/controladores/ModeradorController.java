package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ItemModeradorDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RevisionNegocioDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/moderador")
@RequiredArgsConstructor
public class ModeradorController {

    private final ModeradorServicio moderadorServicio;

    @GetMapping("/obtener-datos-moderador/{idModerador}")
    public ResponseEntity<MensajeDTO<ItemModeradorDTO>> obtenerDatosModerador(@PathVariable String idModerador) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false,moderadorServicio.obtenerInfoModerador(idModerador)));
    }

    @PutMapping("/aprobar-negocio")
    public ResponseEntity<MensajeDTO<String>> aprobarNegocio(@Valid @RequestBody RevisionNegocioDTO negocioDTO) throws Exception{
        moderadorServicio.aprobarNegocio(negocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio aprobado con éxito."));
    }
    @PutMapping("/rechazar-negocio-propio")
    public ResponseEntity<MensajeDTO<String>> rechazarNegocio(@Valid @RequestBody RevisionNegocioDTO negocioDTO) throws Exception{
        moderadorServicio.rechazarNegocio(negocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio rechazado con éxito."));
    }

}
