package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.dto.ClienteDTO.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.NegocioDTO.ItemNegocioDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.RegistroReseniaDTO;
import co.edu.uniquindio.proyecto.dto.ReseniaDTO.ResponderReseniaDTO;
import co.edu.uniquindio.proyecto.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.proyecto.modelo.TipoMedioTransporte;
import co.edu.uniquindio.proyecto.modelo.Ubicacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReseniaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServicio clienteServicio;
    private final ReseniaServicio reseniaServicio;
    private final CuentaServicio cuentaServicio;

    @PutMapping("/editar-cliente")
    public ResponseEntity<MensajeDTO<String>> actualizarUsuario(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO) throws Exception{
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Usuario actualizado correctamente."));
    }

    @DeleteMapping("/eliminar-cliente/{idCliente}")
    public ResponseEntity<MensajeDTO<String>> eliminarCliente(@PathVariable String idUsuario) throws Exception { //Que retorne el id de la cuenta eliminada
        clienteServicio.eliminarCliente(idUsuario);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Cliente eliminado correctamente "+idUsuario));
    }

    @GetMapping("/obtener-cliente/{idCuenta}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerUsuario(@PathVariable String idCuenta) throws Exception{

        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                clienteServicio.obtenerCliente(idCuenta) ) );
    }

    @PutMapping("/agregar-negocio-favoritos/{idNegocio}/{idCliente}")
    public ResponseEntity<MensajeDTO<String>> agregarNegocioFavorito(@PathVariable String idCliente,@PathVariable String idNegocio) throws Exception{
        clienteServicio.agregarNegocioFavorito(idCliente,idNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio agregado a la lista de favoritos correctamente"));
    }

    @DeleteMapping("/eliminar-negocio-favorito/{idNegocio}/{idCliente}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocioFavorito(@PathVariable String idCliente,@PathVariable String idNegocio) throws ResourceNotFoundException {
        clienteServicio.eliminarNegocioFavorito(idCliente,idNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio "+idNegocio+" eliminado de la lista de favoritos"));
    }

    @GetMapping("/listar-negocios-favoritos/{idCliente}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegociosFavoritos(@PathVariable String idCliente) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false,clienteServicio.listarNegociosFavoritos(idCliente)));
    }

    @GetMapping("/solicitar-Ruta/{idCliente}")
    public ResponseEntity<MensajeDTO<String>> solicitarRuta(@PathVariable String idCliente,@Valid @RequestBody Ubicacion ubicacionDestino,@Valid @RequestBody TipoMedioTransporte medioTransporte ) throws ResourceNotFoundException {
        clienteServicio.solicitarRuta(idCliente,ubicacionDestino, medioTransporte);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Ruta encontrada correctamente"));
    }

    @PutMapping("/actualizar-ubicacion/{idCliente}")
    public ResponseEntity<MensajeDTO<String>> actualizarUbicacion(@PathVariable String idCliente,double longitud, double latitud) throws Exception{
        clienteServicio.actualizarUbicacion(idCliente,longitud,latitud);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Ubicación actualizada correctamente."));
    }

    @PostMapping("/registrar-resenia")
    public ResponseEntity<MensajeDTO<String>>crearResenia(@Valid @RequestBody RegistroReseniaDTO registroReseniaDTO) throws Exception{
        reseniaServicio.crearResenia(registroReseniaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Resenia registrada correctamente."));
    }

    @PutMapping("/responder-resenia")
    public ResponseEntity<MensajeDTO<String>> responderResenia(@Valid @RequestBody ResponderReseniaDTO responderReseniaDTO) throws Exception{
        reseniaServicio.responderResenia(responderReseniaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Comentario respondido correctamente."));
    }
    @GetMapping("/recomendar-lugares/{idCliente}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> recomendarLugares(@PathVariable String idCliente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.recomendarLugares(idCliente)));
    }


}
