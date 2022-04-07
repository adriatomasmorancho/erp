/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.serveis.ComandaProfessorServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.LineaComandaServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.ProducteServiceInterface;
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

    @Autowired
    private ProducteServiceInterface producteService;

    @Autowired
    private LineaComandaServiceInterface lineaComandaService;

    @GetMapping("/comandesProfessor") //Pàgina productes de l'aplicació localhost:5050
    public String comandesProfessor(Model model, ComandaProfessor id_comanda, @AuthenticationPrincipal User username) {

        var comandesProfessor = comandaProfessorService.llistarComandesProfessor();
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

        return "comandesProfessor";
    }

    @GetMapping("/crearComandaProfessor") //URL a la pàgina amb el formulari de les dades del producte
    public String crearComandaProfessor(Model model, ComandaProfessor comandaProfessor) {
        var data = comandaProfessorService.getCurrentDate();
        comandaProfessor.setData(data); //Posa la data actual en el camp Data Creació al crear una comanda
        log.info("FECHA:::: " + data);
        model.addAttribute("data", data);
        var productes = producteService.llistarProductes();
        var lineasComanda = lineaComandaService.llistarLineaComanda();
        model.addAttribute("lineasComanda", lineasComanda);
        model.addAttribute("productes", productes);

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

    @GetMapping("/eliminarComandaProfessor/{id_comanda}")
    public String eliminarComandaProfessor(ComandaProfessor comandaProfessor) {
        comandaProfessorService.eliminarComandaProfessor(comandaProfessor);
        return "redirect:/comandesProfessor";
    }

    @GetMapping("/mesInfoComandaProfessor/{id_comanda}")
    public String editar(ComandaProfessor comandaProfessor, Model model) {

        log.info(String.valueOf(comandaProfessor.getId_comanda()));
        comandaProfessor = comandaProfessorService.cercarComandaProfessor(comandaProfessor);
        model.addAttribute("comandaProfessor", comandaProfessor);

        return "mesInfoComandaProfessor";
    }

}
