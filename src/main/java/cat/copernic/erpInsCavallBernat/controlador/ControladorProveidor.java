package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.Proveidor;
import cat.copernic.erpInsCavallBernat.serveis.ProveidorServiceInterface;
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
public class ControladorProveidor {

    @Autowired
    private ProveidorServiceInterface proveidorService;

    @GetMapping("/proveidors") //Pàgina proveidors de l'aplicació localhost:8080
    public String proveidors(Model model, @AuthenticationPrincipal User username, Proveidor cif) {

        var proveidors = proveidorService.llistarAllProveidors();
        model.addAttribute("proveidors", proveidors);
        
        var miRol = proveidorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        return "proveidors";
    }

    @GetMapping("/crearProveidor") //URL a la pàgina amb el formulari de les dades del proveidor
    public String crearProveidor(Model model, @AuthenticationPrincipal User username, Proveidor proveidor) {
        
        var miRol = proveidorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

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
    public String editar(Model model, @AuthenticationPrincipal User username, Proveidor proveidor) {

        log.info(String.valueOf(proveidor.getCif()));
        proveidor = proveidorService.cercarProveidor(proveidor);
        model.addAttribute("proveidor", proveidor);
        
        var miRol = proveidorService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        return "editarProveidor";
    }
    
    @GetMapping("/activarProveidor/{cif}")
    public String activarUsuari(Proveidor cif) {
        proveidorService.activarProveidor(cif);

        return "redirect:/proveidors"; //Retornem a la pàgina inici mitjançant redirect
    }
    
    @GetMapping("/desactivarProveidor/{cif}")
    public String desactivarUsuari(Proveidor cif) {
        proveidorService.desactivarProveidor(cif);

        return "redirect:/proveidors"; //Retornem a la pàgina inici mitjançant redirect
    }

}
