/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.ComandaAdministrador;
import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.model.LineaComanda;
import cat.copernic.erpInsCavallBernat.model.crearCentralitzada;
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
        if (myList.size() >= 3) {
            String finalDatePlusTime = myList.get(2) + "-" + myList.get(1) + "-" + myList.get(0);
            model.addAttribute("data_min_input", finalDatePlusTime);
        }

        var miRol = comandaProfessorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        return "crearComandaAdministrador"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }

    @GetMapping("/editarComandaAdministrador/{id_comanda_centralitzada}") //URL a la pàgina amb el formulari de les dades del producte
    public String editarComandaAdministrador(@AuthenticationPrincipal User username, ComandaAdministrador id_comanda_centralitzada, Model model) {
        var comandesProfessor = comandaProfessorService.llistarComandesProfessor();
        model.addAttribute("comandesProfessor", comandesProfessor);

        //Limitar el día actual com a mínim per a escollir en la Data Arribada
        var today = comandaProfessorService.getActualDatePlusDays(0);
        List<String> myList = Arrays.asList(today.split("/", -1));
        if (myList.size() >= 3) {
            String finalDatePlusTime = myList.get(2) + "-" + myList.get(1) + "-" + myList.get(0);
            model.addAttribute("data_min_input", finalDatePlusTime);
        }

        var miRol = comandaProfessorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        return "editarComandaAdministrador"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }

    @PostMapping("/guardarComandaAdministrador") //action = guardarProveidor
    public String guardarComandaAdministrador(@Valid ComandaAdministrador comandaAdministrador, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "comandesAdministrador";
        }
        var dataArribada = comandaAdministrador.getData_Arribada();
        var dia = dataArribada.substring(8, dataArribada.length());
        var mes = dataArribada.substring(5, 7);
        var año = dataArribada.substring(0, 4);
        var fecha = dia + "/" + mes + "/" + año;
        log.info("FECHA:::: " + fecha);
        comandaAdministrador.setData_Arribada(fecha);
        //Create comanda Centralitzada
        comandaAdministradorService.crearComandaAdministrador(comandaAdministrador);

        return "redirect:/editarComandaComandesAdministrador/" + comandaAdministrador.getId_comanda_centralitzada();
    }

    @PostMapping("/guardarComandaAdministradorEditada") //action = guardarProveidor
    public String guardarComandaAdministradorEditada(@Valid ComandaAdministrador comandaAdministrador, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "comandesAdministrador";
        }
        var dataArribada = comandaAdministrador.getData_Arribada();
        var dia = dataArribada.substring(8, dataArribada.length());
        var mes = dataArribada.substring(5, 7);
        var año = dataArribada.substring(0, 4);
        var fecha = dia + "/" + mes + "/" + año;
        log.info("FECHA:::: " + fecha);
        comandaAdministrador.setData_Arribada(fecha);
        //Create comanda Centralitzada
        comandaAdministradorService.crearComandaAdministrador(comandaAdministrador);

        return "redirect:/comandesAdministrador";
    }

    @GetMapping("/editarComandaComandesAdministrador/{id_comanda_centralitzada}")
    public String editarComandaComandesAdministrador(@AuthenticationPrincipal User username, Model model, ComandaAdministrador id_comanda_centralitzada) {
        crearCentralitzada cc = new crearCentralitzada();
        cc.setCa(id_comanda_centralitzada);
        model.addAttribute("crearComanda", cc);

        //Obtenir la dataArribada de la comandaCentralitzada
        var comandesAdministrador = comandaAdministradorService.llistarComandesAdministrador();
        var idComanda = id_comanda_centralitzada.getId_comanda_centralitzada();
        var dataComanda = "";

        for (var comanda : comandesAdministrador) {
            if (idComanda == comanda.getId_comanda_centralitzada()) {
                dataComanda = comanda.getData_Arribada();
            }
        }
        model.addAttribute("dataComanda", dataComanda);

        //Comandes ja centralitzades amb l'id_centralitzada de la comandaAdmin actual
        var comandesProfessorCentralitzadesWithId = comandaProfessorService.llistarComandesProfessorWhereCentralitzada(id_comanda_centralitzada.getId_comanda_centralitzada());
        model.addAttribute("comandesProfessorCentralitzades", comandesProfessorCentralitzadesWithId);

        //Coamndes NO CENTRALITZADES amb la data de la comanda igual que la de la centralitzada seleccionada
        var comandesProfessorCentralitzadesWithDate = comandaProfessorService.llistarComandesProfessorWhereIsCentralitzada(dataComanda);
        log.info("COMANDES:::: " + comandesProfessorCentralitzadesWithDate);
        model.addAttribute("comandesProfessorNoCentralitzades", comandesProfessorCentralitzadesWithDate);

        //Set Id comanda centralitzada
        model.addAttribute("centralitzada_id", id_comanda_centralitzada);

        if (comandesProfessorCentralitzadesWithDate.isEmpty()) {
            log.info("YESSSSSSSSSS");
            model.addAttribute("comandesProfessorCentralitzadesWithDateEmpty", "true");
        } else {
            model.addAttribute("comandesProfessorCentralitzadesWithDateEmpty", "false");
        }

        //Create Title to show with date
        String textTitol = "Comandes Centralitzades " + id_comanda_centralitzada.getData_Arribada();
        model.addAttribute("textTitol", textTitol);

        var miRol = comandaProfessorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        return "afegirComandesComandaAdministrador";
    }

    @GetMapping("/eliminarComandaComandesAdministrador/{id_comanda_centralitzada}")
    public String eliminarComandaComandesAdministrador(@AuthenticationPrincipal User username, Model model, ComandaAdministrador id_comanda_centralitzada) {
        //remove id_centralitzada for each comandaProfessor
        for (ComandaProfessor cp : comandaProfessorService.llistarComandesProfessor()) {
            if (id_comanda_centralitzada.getId_comanda_centralitzada() == cp.getId_centralitzada()) {
                cp.setId_centralitzada(0);
                comandaProfessorService.crearComandaProfessor(cp);
            }
        }

        //Remove comandaAdministrador
        comandaAdministradorService.eliminarComandaAdministrador(id_comanda_centralitzada);

        return "redirect:/comandesAdministrador";
    }

    @GetMapping("/removeComandaFromComandesAdministrador/{id_comanda}")
    public String removeComandaFromComandesAdministrador(@AuthenticationPrincipal User username, Model model, ComandaProfessor id_comanda) {
        //remove id_centralitzada
        ComandaProfessor myNewComanda = comandaProfessorService.cercarComandaProfessor(id_comanda);
        long centralitzadaId = myNewComanda.getId_centralitzada();
        myNewComanda.setId_centralitzada(0);
        comandaProfessorService.crearComandaProfessor(myNewComanda);

        return "redirect:/editarComandaComandesAdministrador/" + centralitzadaId;
    }

    @GetMapping("/showProductesFromCentralitzada/{id_comanda_centralitzada}")
    public String showProductesFromCentralitzada(@AuthenticationPrincipal User username, Model model, ComandaAdministrador id_comanda_centralitzada) {

        var lineaComanda = comandaProfessorService.llistarComandesProductesWhereCentralitzada(id_comanda_centralitzada.getId_comanda_centralitzada());
        model.addAttribute("lineaComandes", lineaComanda);

        var miRol = comandaProfessorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        //Calcular total
        double total = 0;
        for (LineaComanda lc : lineaComanda) {
            total += lc.getId_Producte().getPreu() * lc.getQuantitat();
        }
        String finalTotal = "Total: " + Double.toString(total) + "€";
        model.addAttribute("total", finalTotal);

        return "showComandaAdministrador";
    }

    @PostMapping("/afegirComandaComandesAdministrador") //action = guardarProveidor
    public String afegirComandaComandesAdministrador(@Valid crearCentralitzada crearCentralitzada, Errors errors) {
        log.info(crearCentralitzada.getCa().toString());

        //Set centralitzada's id
        crearCentralitzada.getCp().setId_centralitzada(crearCentralitzada.getCa().getId_comanda_centralitzada());
        comandaProfessorService.crearComandaProfessor(crearCentralitzada.getCp());

        return "redirect:/editarComandaComandesAdministrador/" + crearCentralitzada.getCa().getId_comanda_centralitzada();
    }

    @GetMapping("/eliminarComandaAdministrador/{id_comanda_centralitzada}")
    public String eliminarComandaAdministrador(ComandaAdministrador comandaAdministrador) {
        comandaAdministradorService.eliminarComandaAdministrador(comandaAdministrador);
        return "redirect:/comandesAdministrador";
    }

    @GetMapping("/comandesProfessorCentralitzades/{id_comanda_centralitzada}") //Pàgina productes de l'aplicació localhost:5050
    public String comandesProfessorCentralitzades(Model model, ComandaProfessor id_ComandaAdministrador, @AuthenticationPrincipal User username) {

        List comandesProfessor = comandaProfessorService.llistarComandesProfessorWhereCentralitzada(id_ComandaAdministrador.getId_centralitzada());

        log.info("USERNAME::: " + username);
        var usuari = username.getUsername();
        log.info("USUARI::: " + usuari);
        var fecha = comandaProfessorService.getCurrentDate();
        log.info("FECHA:::: " + fecha);

        model.addAttribute("comandesProfessor", comandesProfessor);
        model.addAttribute("usuari", usuari);
        model.addAttribute("fecha", fecha);

        //model.addAttribute("myId", myId);
        var misComandas = comandaProfessorService.getMisComandes(username);
        model.addAttribute("misComandas", misComandas);

        var miRol = comandaProfessorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        return "comandesProfessorCentralitzades";
    }

}
