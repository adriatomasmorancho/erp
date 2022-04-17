/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.model.LineaComanda;
import cat.copernic.erpInsCavallBernat.serveis.ComandaProfessorServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.LineaComandaServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.ModulServiceInterface;
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
 * @author ivan
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
    @Autowired
    private ModulServiceInterface modulComandaService;

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

        //model.addAttribute("myId", myId);
        var misComandas = comandaProfessorService.getMisComandes(username);
        model.addAttribute("misComandas", misComandas);

        return "comandesProfessor";
    }

    @GetMapping("/crearComandaProfessor") //URL a la pàgina amb el formulari de les dades del producte
    public String crearComandaProfessor(Model model, ComandaProfessor comandaProfessor, @AuthenticationPrincipal User username) {
        var data = comandaProfessorService.getCurrentDate();
        comandaProfessor.setData(data); //Posa la data actual en el camp Data Creació al crear una comanda
        log.info("FECHA:::: " + data);
        model.addAttribute("data", data);
        var productes = producteService.llistarProductes();
        var lineasComanda = lineaComandaService.llistarLineaComanda();
        var moduls = modulComandaService.llistarModuls();
        var rol = comandaProfessorService.getRolUserCurrent(username);
        var ids = comandaProfessorService.getIds(username);
        model.addAttribute("lineasComanda", lineasComanda);
        model.addAttribute("productes", productes);
        model.addAttribute("moduls", moduls);
        model.addAttribute("rol", rol);
        model.addAttribute("ids", ids);

        return "crearComandaProfessor"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }
    
    @GetMapping("/crearComandaProfessorProductes/{id_comanda}") //Pàgina productes de l'aplicació localhost:5050
    public String productesComandaProfessor(Model model, ComandaProfessor id_comanda) {

        var lineaComanda = lineaComandaService.llistarLineaComandaWhereComanda(id_comanda);
        model.addAttribute("lineaComandes", lineaComanda);
        model.addAttribute("id_comanda", id_comanda.getId_comanda());

        return "crearComandaProfessorProductes";
    }

    @PostMapping("/guardarComandaProfessor") //action = guardarProveidor
    public String guardarComandaProfessor(@Valid ComandaProfessor comandaProfessor, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearComandaProfessor";
        }
        var dataArribada = comandaProfessor.getData_Arribada();
        var dia = dataArribada.substring(8, dataArribada.length());
        var mes = dataArribada.substring(5, 7);
        var año = dataArribada.substring(0, 4);
        var fecha = dia + "/" + mes + "/" + año;
        comandaProfessor.setData_Arribada(fecha);
        comandaProfessorService.crearComandaProfessor(comandaProfessor);

        return "redirect:/comandesProfessor";
    }
    
    @GetMapping("/editarComandaProfessorProductes/{comandaProfessor}") //URL a la pàgina amb el formulari de les dades del producte
    public String editarComandaProfessorProductes(Model model, ComandaProfessor comandaProfessor, @AuthenticationPrincipal User username) {
        var data = comandaProfessorService.getCurrentDate();
        comandaProfessor.setData(data); //Posa la data actual en el camp Data Creació al crear una comanda
        log.info("FECHA:::: " + data);
        model.addAttribute("data", data);
        var productes = producteService.llistarProductes();
        var lineasComanda = lineaComandaService.llistarLineaComanda();
        var moduls = modulComandaService.llistarModuls();
        var rol = comandaProfessorService.getRolUserCurrent(username);
        var ids = comandaProfessorService.getIds(username);
        model.addAttribute("lineasComanda", lineasComanda);
        model.addAttribute("productes", productes);
        model.addAttribute("moduls", moduls);
        model.addAttribute("rol", rol);
        model.addAttribute("ids", ids);

        return "crearComandaProfessor"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }
    
    @GetMapping("/afegirProducteComanda/{id_comanda}") //action = guardarProveidor
    public String afegirProducteComanda(Model model, ComandaProfessor id_comanda, Errors errors, LineaComanda lineaComanda) {
        var productes = producteService.llistarProductes();
        model.addAttribute("productes", productes);
        model.addAttribute("id_comanda", id_comanda.getId_comanda());
        
        return "crearComandaProfessorAfegirProducte";
    }
    
    @GetMapping("/editarLineaComanda/{lineaComanda}") //action = editarComanda
    public String editarProducteComanda(Model model, LineaComanda lineaComanda, Errors errors) {
        var productes = producteService.llistarProductes();
        model.addAttribute("productes", productes);
        model.addAttribute("lineaComanda", lineaComanda);
        
        return "crearComandaProfessorEditarProducte";
    }
    
    @PostMapping("/guardarLineaComanda") //action = guardarProveidor
    public String guardarLineaComanda(@Valid LineaComanda lineaComanda, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "guardarLineaComanda";
        }
        
        //Crear Linea Comanda
        lineaComandaService.crearLineaComanda(lineaComanda);

        return "redirect:/comandesProfessor";
    }
    
    @GetMapping("/eliminarLineaComanda/{id_linea_comanda}") //action = guardarProveidor
    public String eliminarLineaComanda(@Valid LineaComanda id_linea_comanda, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "guardarLineaComanda";
        }
        
        //Eliminar Linea Comanda
        lineaComandaService.eliminarLineaComanda(id_linea_comanda);

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
