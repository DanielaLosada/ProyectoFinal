package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.NegocioDTO.RevisionNegocioDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModeradorServicioTest {
    @Autowired
    ModeradorServicio moderadorServicio;


    @Test
    public void obtenerDatosTest() throws Exception{
        Assertions.assertNotNull(moderadorServicio.obtenerInfoModerador("Moderador#1"));
    }

    @Test
    public void aprobarTest() throws Exception{
        RevisionNegocioDTO revisionNegocioDTO = new RevisionNegocioDTO(
                "661b30a613481c7f49a585b9",
                "Excelente negocio innovaodr",
                "Moderador#1"
        );
        moderadorServicio.aprobarNegocio(revisionNegocioDTO);
        Assertions.assertNotNull(revisionNegocioDTO.idNegocio());
    }

    @Test
    public void rechazarTest() throws Exception{
        RevisionNegocioDTO revisionNegocioDTO = new RevisionNegocioDTO(
                "661b30a613481c7f49a585b9",
                "No cumple con los requisitos",
                "Moderador1"
        );
        moderadorServicio.rechazarNegocio(revisionNegocioDTO);
        Assertions.assertNotNull(revisionNegocioDTO.idNegocio());

    }

}
