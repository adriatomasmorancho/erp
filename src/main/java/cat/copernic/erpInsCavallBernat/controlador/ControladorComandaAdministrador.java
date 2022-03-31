/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;


import cat.copernic.erpInsCavallBernat.model.ComandaAdministrador;
import cat.copernic.erpInsCavallBernat.serveis.ComandaAdministradorServiceInterface;
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
    private ComandaAdministradorServiceInterface comandaAdministradorService;
    
    @GetMapping("/comandesAdministrador") //Pàgina productes de l'aplicació localhost:5050
    public String comandesAdministrador(Model model, @AuthenticationPrincipal ComandaAdministrador id_ComandaAdministrador) {
        
        var comandesAdministrador = comandaAdministradorService.llistarComandesAdministrador();

        model.addAttribute("comandesAdministrador", comandesAdministrador);
        
        return "comandesAdministrador";
    }
    
    @GetMapping("/crearComandaAdministrador") //URL a la pàgina amb el formulari de les dades del producte
    public String crearAdministrador(ComandaAdministrador comandaAdministrador) {

        return "crearComandaAdministrador"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }
    
    @PostMapping("/guardarComandaAdministrador") //action = guardarProveidor
    public String guardarComandaAdministrador(@Valid ComandaAdministrador comandaAdministrador, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearComandaAdministrador";
        }
        comandaAdministradorService.crearComandaAdministrador(comandaAdministrador);
        return "redirect:/comandesAdministrador";
    }
    
   @GetMapping("/eliminarComandaAdministrador/{id_ComandaAdministrador}")
    public String eliminarComandaAdministrador(ComandaAdministrador comandaAdministrador) {
        comandaAdministradorService.eliminarComandaAdministrador(comandaAdministrador);
        return "redirect:/comandesAdministrador";
    }
    
    @GetMapping("/editarComandaAdministrador/{id_ComandaAdministrador}")
    public String editarComandaAdministrador(ComandaAdministrador comandaAdministrador, Model model) {

        log.info(String.valueOf(comandaAdministrador.getId_comandaCentralitzada()));
        comandaAdministrador = comandaAdministradorService.cercarComandaAdministrador(comandaAdministrador);
        model.addAttribute("comandaAdministrador", comandaAdministrador);

        return "editarComandaAdministrador";
    }
    
    @GetMapping("/mesInfoComandaAdministrador/{id_ComandaAdministrador}")
    public String editar(ComandaAdministrador comandaAdministrador, Model model) {

        log.info(String.valueOf(comandaAdministrador.getId_comandaCentralitzada()));
        comandaAdministrador = comandaAdministradorService.cercarComandaAdministrador(comandaAdministrador);
        model.addAttribute("comandaAdministrador", comandaAdministrador);

        return "mesInfoComandaAdministrador";
    }
    
}
