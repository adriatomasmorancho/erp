/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.Producte;
import cat.copernic.erpInsCavallBernat.serveis.ProducteServiceInterface;
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
public class ControladorProducte {
    
    @Autowired
    private ProducteServiceInterface producteService;
    
    @GetMapping("/productes") //Pàgina productes de l'aplicació localhost:5050
    public String productes(Model model, @AuthenticationPrincipal Producte id_Producte) {
        
        var productes = producteService.llistarProductes();

        model.addAttribute("productes", productes);
        
        return "productes";
    }
    
    @GetMapping("/crearProducte") //URL a la pàgina amb el formulari de les dades del producte
    public String crearProducte(Producte producte) {

        return "crearProducte"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }
    
    @PostMapping("/guardarProducte") //action = guardarProveidor
    public String guardarProducte(@Valid Producte producte, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearProducte";
        }
        producteService.crearProducte(producte);
        return "redirect:/productes";
    }
    
    @GetMapping("/eliminarProducte/{id_Producte}")
    public String eliminarProducte(Producte producte) {
        producteService.eliminarProducte(producte);
        return "redirect:/productes";
    }
    
    @GetMapping("/editarProducte/{id_Producte}")
    public String editar(Producte producte, Model model) {

        log.info(String.valueOf(producte.getId_Producte()));
        producte = producteService.cercarProducte(producte);
        model.addAttribute("producte", producte);

        return "editarProducte";
    }
    
}
