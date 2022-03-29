/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;


import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.serveis.ComandaProfessorServiceInterface;
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
 * @author adria
 */

@Controller
@Slf4j
public class ControladorComandaAdministrador {
    
    @Autowired
    private ComandaProfessorServiceInterface comandaProfessorService;
    
    @GetMapping("/comandesAdministrador") //Pàgina productes de l'aplicació localhost:5050
    public String comandesAdministrador(Model model, @AuthenticationPrincipal ComandaProfessor id_ComandaProfessor) {
        
        var comandesProfessor = comandaProfessorService.llistarComandesProfessor();

        model.addAttribute("comandesProfessor", comandesProfessor);
        
        return "comandesAdministrador";
    }
    
    @GetMapping("/crearComandaAdministrador") //URL a la pàgina amb el formulari de les dades del producte
    public String crearAdministrador(ComandaProfessor comandaProfessor) {

        return "crearComandaAdministrador"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }
    
    @PostMapping("/guardarComandaAdministrador") //action = guardarProveidor
    public String guardarComandaAdministrador(@Valid ComandaProfessor comandaProfessor, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearComandaAdministrador";
        }
        comandaProfessorService.crearComandaProfessor(comandaProfessor);
        return "redirect:/comandesAdministrador";
    }
    
   @GetMapping("/eliminarComandaAdministrador/{id_ComandaProfessor}")
    public String eliminarComandaAdministrador(ComandaProfessor comandaProfessor) {
        comandaProfessorService.eliminarComandaProfessor(comandaProfessor);
        return "redirect:/comandesAdministrador";
    }
    
    @GetMapping("/editarComandaAdministrador/{id_ComandaProfessor}")
    public String editarComandaAdministrador(ComandaProfessor comandaProfessor, Model model) {

        log.info(String.valueOf(comandaProfessor.getId_ComandaProfessor()));
        comandaProfessor = comandaProfessorService.cercarComandaProfessor(comandaProfessor);
        model.addAttribute("comandaProfessor", comandaProfessor);

        return "editarComandaAdministrador";
    }
    
    @GetMapping("/mesInfoComandaAdministrador/{id_ComandaProfessor}")
    public String editar(ComandaProfessor comandaProfessor, Model model) {

        log.info(String.valueOf(comandaProfessor.getId_ComandaProfessor()));
        comandaProfessor = comandaProfessorService.cercarComandaProfessor(comandaProfessor);
        model.addAttribute("comandaProfessor", comandaProfessor);

        return "mesInfoComandaAdministrador";
    }
    
}
