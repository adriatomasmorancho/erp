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
import org.springframework.security.core.userdetails.User;
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
public class ControladorComandaProfessor {
    
    @Autowired
    private ComandaProfessorServiceInterface comandaProfessorService;
    
    @GetMapping("/comandesProfessor") //Pàgina productes de l'aplicació localhost:5050
    public String comandesProfessor(Model model, ComandaProfessor id_ComandaProfessor, @AuthenticationPrincipal User username) {
        
        var comandesProfessor = comandaProfessorService.llistarComandesProfessor();
        var rol = comandaProfessorService.getRolUserCurrent(username);

        model.addAttribute("comandesProfessor", comandesProfessor);
        model.addAttribute("rol", rol);
        
        return "comandesProfessor";
    }
    
    @GetMapping("/crearComandaProfessor") //URL a la pàgina amb el formulari de les dades del producte
    public String crearComandaProfessor(ComandaProfessor comandaProfessor) {

        return "crearComandaProfessor"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }
    
    @PostMapping("/guardarComandaProfessor") //action = guardarProveidor
    public String guardarComandaProfessor(@Valid ComandaProfessor comandaProfessor, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearComandaProfessor";
        }
        comandaProfessorService.crearComandaProfessor(comandaProfessor);
        return "redirect:/comandesProfessor";
    }
    
    @GetMapping("/eliminarComandaProfessor/{id_ComandaProfessor}")
    public String eliminarComandaProfessor(ComandaProfessor comandaProfessor) {
        comandaProfessorService.eliminarComandaProfessor(comandaProfessor);
        return "redirect:/comandesProfessor";
    }
    
    @GetMapping("/mesInfoComandaProfessor/{id_Comanda_Professor}")
    public String editar(ComandaProfessor comandaProfessor, Model model) {

        log.info(String.valueOf(comandaProfessor.getId_Comanda_Professor()));
        comandaProfessor = comandaProfessorService.cercarComandaProfessor(comandaProfessor);
        model.addAttribute("comandaProfessor", comandaProfessor);

        return "mesInfoComandaProfessor";
    }
    
}
