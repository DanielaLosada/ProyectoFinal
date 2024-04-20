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
        Assertions.assertNotNull(moderadorServicio.obtenerInfoModerador("6622e7b7f206fe603f114bdf"));
    }

    @Test
    public void aprobarTest() throws Exception{
        RevisionNegocioDTO revisionNegocioDTO = new RevisionNegocioDTO(
                "6622a1cb66aafa3801ec15f0",
                "Excelente negocio innovaodr",
                "6622e7b7f206fe603f114bdf"
        );
        moderadorServicio.aprobarNegocio(revisionNegocioDTO);
        Assertions.assertNotNull(revisionNegocioDTO.idNegocio());
    }

    @Test
    public void rechazarTest() throws Exception{
        RevisionNegocioDTO revisionNegocioDTO = new RevisionNegocioDTO(
                "6622a1ee2b00de270d9d23e3",
                "No cumple con los requisitos",
                "6622e7b7f206fe603f114bdf"
        );
        moderadorServicio.rechazarNegocio(revisionNegocioDTO);
        Assertions.assertNotNull(revisionNegocioDTO.idNegocio());

    }

}
