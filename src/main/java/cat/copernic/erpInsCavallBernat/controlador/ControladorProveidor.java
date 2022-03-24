/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.Proveidor;
import cat.copernic.erpInsCavallBernat.serveis.ProveidorServiceInterface;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ivan
 */

@Controller
@Slf4j
public class ControladorProveidor {
    
    @Autowired
    private ProveidorServiceInterface proveidorService;
    
    @GetMapping("/proveidors") //Pàgina proveidors de l'aplicació localhost:8080
    public String proveidors(Model model, @AuthenticationPrincipal Proveidor cif) {
        
        var proveidors = proveidorService.llistarProveidors();

        model.addAttribute("proveidors", proveidors);
        
        return "proveidors";
    }
    
    @GetMapping("/crearProveidor") //URL a la pàgina amb el formulari de les dades del proveidor
    public String crearProveidor(Proveidor proveidor) {

        return "crearProveidor"; //Retorna la pàgina on es mostrarà el formulari de les dades dels proveidors
    }
    
    @PostMapping("/guardarProveidor") //action = guardarProveidor
    public String guardarProveidor(@Valid Proveidor proveidor, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearProveidor";
        }
        proveidorService.crearProveidor(proveidor);
        return "redirect:/proveidors";
    }
    
    @GetMapping("/eliminarProveidor/{cif}")
    public String eliminarProveidor(Proveidor proveidor) {
        proveidorService.eliminarProveidor(proveidor);
        return "redirect:/proveidors";
    }
    
    @GetMapping("/editarProveidor/{cif}")
    public String editar(Proveidor proveidor, Model model) {

        log.info(String.valueOf(proveidor.getCif()));
        proveidor = proveidorService.cercarProveidor(proveidor);
        model.addAttribute("proveidor", proveidor);

        return "editarProveidor";
    }
    
}
