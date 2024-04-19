package co.edu.uniquindio.proyecto.servicios.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImagenesServicio {
    Map subiImagen(MultipartFile imagen) throws Exception;

    Map eliminarImagen(String idImagen) throws Exception;
}
