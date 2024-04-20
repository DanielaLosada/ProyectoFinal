package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.controladores.ImagenesController;
import co.edu.uniquindio.proyecto.dto.ImagenDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.implementacion.ImagenesServicioImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
public class ImagenesServicioTest {

    @InjectMocks
    private ImagenesController imagenesController;

    @Mock
    private ImagenesServicioImpl imagenesServicio;

    @Test
    void testSubir() throws Exception {
        // Arrange
        MultipartFile imagen = mock(MultipartFile.class);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Imagen subida correctamente");
        when(imagenesServicio.subiImagen(imagen)).thenReturn(respuesta);

        // Act
        ResponseEntity<MensajeDTO<Map>> responseEntity = imagenesController.subir(imagen);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(respuesta, responseEntity.getBody().respuesta());
    }

    @Test
    void testEliminar() throws Exception {
        // Arrange
        ImagenDTO imagenDTO = new ImagenDTO("1", "");
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Imagen eliminada correctamente");
        when(imagenesServicio.eliminarImagen("1")).thenReturn(respuesta);

        // Act
        ResponseEntity<MensajeDTO<Map>> responseEntity = imagenesController.eliminarImagen(imagenDTO);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(respuesta, responseEntity.getBody().respuesta());
    }


}
