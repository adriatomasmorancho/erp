/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat;

import cat.copernic.erpInsCavallBernat.model.Modul;
import cat.copernic.erpInsCavallBernat.serveis.ModulServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author adria
 */
@SpringBootTest
public class ModulTest {
    
   @Autowired
    private ModulServiceInterface modulService;
   
   Modul testModul;
   
   @BeforeEach
   void info(){
       testModul = new Modul();
       testModul.setNom("M16");
   }
   
   @Test
   public void afegirModul(){
       modulService.crearModul(testModul);
   }
    
   @Test
   public void buscarModul(){
       assertEquals("M03", modulService.findByName("M03").getNom());
   }
   
   @Test
   public void editarModul(){
      modulService.editarModul(modulService.cercarModul(modulService.findByName(testModul.getNom())), "M18");
      assertEquals("M18", modulService.findByName("M18").getNom());
       
   }  
   
   @Test
   public void eliminarModul(){
       modulService.eliminarModul(modulService.findByName("M15"));
       assertEquals(null, modulService.findByName("M15"));
   } 
}
