/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.ComandaAdministrador;
import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.serveis.ComandaAdministradorServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.ComandaProfessorServiceInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
public class ControladorComandaAdministrador {

    @Autowired
    private ComandaAdministradorServiceInterface comandaAdministradorService;

    @Autowired
    private ComandaProfessorServiceInterface comandaProfessorService;

    @GetMapping("/comandesAdministrador") //Pàgina productes de l'aplicació localhost:5050
    public String comandesAdministrador(Model model, @AuthenticationPrincipal User username, ComandaAdministrador id_comanda_centralitzada, ComandaProfessor id_comanda) {

        var comandesAdministrador = comandaAdministradorService.llistarComandesAdministrador();

        model.addAttribute("comandesAdministrador", comandesAdministrador);

        var comandesProfessor = comandaProfessorService.llistarComandesProfessor();

        model.addAttribute("comandesProfessor", comandesProfessor);

        var rol = comandaProfessorService.getRolUserCurrent(username);

        model.addAttribute("rol", rol);
        
        var miRol = comandaAdministradorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        return "comandesAdministrador";
    }

    @GetMapping("/crearComandaAdministrador") //URL a la pàgina amb el formulari de les dades del producte
    public String crearComandaAdministrador(@AuthenticationPrincipal User username, ComandaAdministrador id_comanda_centralitzada, Model model) {
        var comandesProfessor = comandaProfessorService.llistarComandesProfessor();
        model.addAttribute("comandesProfessor", comandesProfessor);
        
        //Limitar el día actual com a mínim per a escollir en la Data Arribada
        var today = comandaProfessorService.getActualDatePlusDays(0);
        List<String> myList = Arrays.asList(today.split("/", -1));
        if(myList.size() >= 3){
            String finalDatePlusTime = myList.get(2) + "-" + myList.get(1) + "-" + myList.get(0);
            model.addAttribute("data_min_input", finalDatePlusTime);
        }
        
        var miRol = comandaProfessorService.rolUsername(username);
        model.addAttribute("miRol", miRol);
        
        return "crearComandaAdministrador"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }

    @PostMapping("/guardarComandaAdministrador") //action = guardarProveidor
    public String guardarComandaAdministrador(@Valid ComandaAdministrador id_comanda_centralitzada, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "comandesAdministrador";
        }
        //Create comanda Centralitzada
        comandaAdministradorService.crearComandaAdministrador(id_comanda_centralitzada);
        
        //Create list and add selected comandes to centralitzar
        List<ComandaProfessor> lcp = new ArrayList<ComandaProfessor>();
        lcp.add(comandaProfessorService.llistarComandesProfessor().get(0));
        
        //Get centralitzada Id
        Long myId = comandaAdministradorService.cercarComandaAdministrador(id_comanda_centralitzada).getId_comanda_centralitzada();
        
        //Push the Id to all comandes involved in the operation
        for(ComandaProfessor cp : lcp){
            cp.setId_centralitzada(myId);
            comandaProfessorService.crearComandaProfessor(cp);
        }
        
        return "redirect:/comandesAdministrador";
    }

    @GetMapping("/eliminarComandaAdministrador/{id_ComandaAdministrador}")
    public String eliminarComandaAdministrador(ComandaAdministrador comandaAdministrador) {
        comandaAdministradorService.eliminarComandaAdministrador(comandaAdministrador);
        return "redirect:/comandesAdministrador";
    }

    @GetMapping("/editarComandaAdministrador/{id_ComandaAdministrador}")
    public String editarComandaAdministrador(ComandaAdministrador comandaAdministrador, Model model) {

        log.info(String.valueOf(comandaAdministrador.getId_comanda_centralitzada()));
        comandaAdministrador = comandaAdministradorService.cercarComandaAdministrador(comandaAdministrador);
        model.addAttribute("comandaAdministrador", comandaAdministrador);

        return "editarComandaAdministrador";
    }

    @GetMapping("/mesInfoComandaAdministrador/{id_ComandaAdministrador}")
    public String editar(ComandaAdministrador comandaAdministrador, Model model) {

        log.info(String.valueOf(comandaAdministrador.getId_comanda_centralitzada()));
        comandaAdministrador = comandaAdministradorService.cercarComandaAdministrador(comandaAdministrador);
        model.addAttribute("comandaAdministrador", comandaAdministrador);

        return "mesInfoComandaAdministrador";
    }
    
    @GetMapping("/comandesProfessorCentralitzades/{id_ComandaAdministrador}") //Pàgina productes de l'aplicació localhost:5050
    public String comandesProfessorCentralitzades(Model model, ComandaProfessor id_ComandaAdministrador, @AuthenticationPrincipal User username) {

        List comandesProfessor = comandaProfessorService.llistarComandesProfessorWhereCentralitzada(id_ComandaAdministrador.getId_centralitzada());
        
        var rol = comandaProfessorService.getRolUserCurrent(username);
        log.info("USERNAME::: " + username);
        var usuari = username.getUsername();
        log.info("USUARI::: " + usuari);
        var fecha = comandaProfessorService.getCurrentDate();
        log.info("FECHA:::: " + fecha);

        model.addAttribute("comandesProfessor", comandesProfessor);
        model.addAttribute("rol", rol);
        model.addAttribute("usuari", usuari);
        model.addAttribute("fecha", fecha);

        //model.addAttribute("myId", myId);
        var misComandas = comandaProfessorService.getMisComandes(username);
        model.addAttribute("misComandas", misComandas);

        return "comandesProfessorCentralitzades";
    }

}
