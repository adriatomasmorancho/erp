package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.serveis.UsuariServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ivan
 */
@Controller
@Slf4j
public class ControladorInici {
    
    @Autowired
    private UsuariServiceInterface usuariService;

    /*Farem que aquest mètode retorni la pàgina inici penjant de de l'arrel de l'aplicacó,
     *passant a ser la pàgina inicial de l'aplicació, la que es mostrarà al escriure localhost:5050
     */
 /*@AuthenticationPrincipal retorna l'usuari autenticat actualment com un objecte User de Spring security*/
    @GetMapping("/") //Arrel de l'aplicació localhost:5050
    public String inici(Model model, @AuthenticationPrincipal User username) {
        log.info("Executant el controlador Spring MVC");
        log.info("L'usuari autenticat és: " + username);
        
        var miRol = usuariService.rolUsername(username);
        model.addAttribute("miRol", miRol);

        return "inici"; //Retorna la pàgina inici
    }

    @GetMapping("/errors/error403")
    public String error403() {
        return "error/error403";
    }

}
   
       
