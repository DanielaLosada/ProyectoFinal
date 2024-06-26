package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.RegistroNegocioDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ItemReseniaDTO;
import co.edu.uniquindio.proyecto.modelo.Resenia;
import co.edu.uniquindio.proyecto.modelo.TipoNegocio;
import co.edu.uniquindio.proyecto.servicios.implementacion.ReseniaServicioImpl;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/negocio")
@RequiredArgsConstructor
public class NegocioController {

    private final NegocioServicio negocioServicio;
    private final ReseniaServicioImpl reseniaServicio;

    @PostMapping("/registrar-negocio")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody RegistroNegocioDTO registroNegocioDTO) throws Exception{
        negocioServicio.crearNegocio(registroNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"El negocio fue creado correctamente."));
    }

    @PutMapping("/editar-negocio")
    public ResponseEntity<MensajeDTO<String>> actualizarNegocio(@Valid @RequestBody ActualizarNegocioDTO actualizarNegocioDTO) throws Exception{
        negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio actualizado con éxito."));
    }
    @GetMapping("/obtener-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> obtenerNegocio(@PathVariable String idNegocio) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerNegocio(idNegocio)));
    }

    @GetMapping("/obtener-negocio-tipo/{tipoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> obtenerNegocioTipo(@PathVariable TipoNegocio tipoNegocio) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtarNegocioTipo(tipoNegocio)));
    }


    @DeleteMapping("/eliminar-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@PathVariable String idNegocio) throws Exception{
        negocioServicio.eliminarNegocio(idNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio eliminado correctamente."));
    }

    @GetMapping("/buscar-negocio/{idUsuario}/{idNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> buscarNegocio(@PathVariable String idNegocio, @PathVariable String idUsuario) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false,negocioServicio.buscarNegocios(idNegocio,idUsuario)));
    }

    @GetMapping("/listar-negocios-usuario/{idUsuario}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegocioPropietario(@PathVariable String idUsuario) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false,negocioServicio.listarNegocioPropietario(idUsuario)));
    }

    @GetMapping("/buscar-negocio/{nombreNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> filtrarNegocioNombre(@PathVariable String nombreNegocio)throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false,negocioServicio.filtarNegocioNombre(nombreNegocio)));
    }

    @GetMapping("/buscar-negocios-tipo/{tipoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegociosPorTipo(@PathVariable TipoNegocio tipoNegocio) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false,negocioServicio.filtarNegocioTipo(tipoNegocio)));
    }

    @GetMapping("/listar-resenias")
    public ResponseEntity<MensajeDTO<List<ItemReseniaDTO>>> listarReseniasNegocio() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, reseniaServicio.listarResenias()));
    }

    @PostMapping("/cambiar-estado-negocio-rechazado-5-dias")
    public ResponseEntity<MensajeDTO<String>> cambiarEstadoNegocioRechazado5Dias() throws Exception{
        negocioServicio.cambiarEstadoNegociosRechazados();
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"El negocio lleva más de 5 dias rechazado, así que su estado pasó a ser inactivo"));
    }

}
