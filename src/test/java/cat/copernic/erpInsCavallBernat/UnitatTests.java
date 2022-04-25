package cat.copernic.erpInsCavallBernat;

import cat.copernic.erpInsCavallBernat.model.Unitat;
import cat.copernic.erpInsCavallBernat.serveis.UnitatServiceInterface;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ivan
 */

@SpringBootTest
public class UnitatTests {

    @Autowired
    private UnitatServiceInterface unitatService;

    Unitat testUnitat;
    String nomUnitat = "Bossa";
    String nouNomUnitat = "Malla";
    
    @BeforeEach
    void info() {
        testUnitat = new Unitat();      
        testUnitat.setNom(nomUnitat);
    }

    @Test
    public void afegirUnitat() {
        unitatService.crearUnitat(testUnitat);
        assertEquals("Bossa", unitatService.findByName(nomUnitat).getNom());
    }
    
    @Test
    public void cercarUnitat() {
        assertEquals(nomUnitat, unitatService.findByName(nomUnitat).getNom());
    }
    
    @Test
    public void editarUnitat() {
        unitatService.editarUnitat(unitatService.cercarUnitat(unitatService.findByName(testUnitat.getNom())), nouNomUnitat);
        assertEquals(nouNomUnitat, unitatService.findByName(nouNomUnitat).getNom());
                
    }

    @Test
    public void eliminarUnitat() {
        unitatService.eliminarUnitat(unitatService.findByName(nouNomUnitat));
        assertEquals(null, unitatService.findByName(nouNomUnitat));
    }

}
