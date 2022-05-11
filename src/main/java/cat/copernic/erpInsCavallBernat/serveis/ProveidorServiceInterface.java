package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Proveidor;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author rpuig
 */

//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface ProveidorServiceInterface {
    public List<Proveidor> llistarProveidors();
    public List<Proveidor> llistarAllProveidors();
    public void crearProveidor(Proveidor proveidor);
    public void eliminarProveidor(Proveidor proveidor);
    public void activarProveidor(Proveidor proveidor);
    public void desactivarProveidor(Proveidor proveidor);
    public Proveidor cercarProveidor(Proveidor proveidor);
    public List<Proveidor> cercarProveidorByCif(String nom);
    public String rolUsername(User username);
}
