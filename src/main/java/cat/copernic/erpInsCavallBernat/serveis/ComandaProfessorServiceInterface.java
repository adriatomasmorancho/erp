package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.model.LineaComanda;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */

//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface ComandaProfessorServiceInterface {
    
    public List<ComandaProfessor> llistarComandesProfessor(); //Mètode que implementarem per llistar comandes
    
    public List<ComandaProfessor> llistarComandesProfessorWhereCentralitzada(long idCentralitzada);
    
    public List<ComandaProfessor> llistarComandesProfessorWhereIsCentralitzada(String date);
    
    public List<LineaComanda> llistarComandesProductesWhereCentralitzada(long idCentralitzada);
   
    public void crearComandaProfessor(ComandaProfessor comandaProfessor); //Mètode que implementarem per afegir una comanda
    
    public void eliminarComandaProfessor(ComandaProfessor comandaProfessor); //Mètode que implementarem per eliminar una comanda
    
    public ComandaProfessor cercarComandaProfessor(ComandaProfessor comandaProfessor); //Mètode que implementarem per cercar una comanda
    
    public String getCurrentDate();
    
    public String getActualDatePlusDays(int days);
    
    public List getMisComandes(User username);
    
    public List getIds(User username);
    
    public String rolUsername(User username);
  
}
