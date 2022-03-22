package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Proveidor;
import java.util.List;

/**
 *
 * @author rpuig
 */

//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface ProveidorServiceInterface {
    
    public List<Proveidor> llistarProveidors(); //Mètode que implementarem per llistar usuaris
    
    public void crearProveidor(Proveidor proveidor); //Mètode que implementarem per afegir un usuari
    
    public void eliminarProveidor(Proveidor proveidor); //Mètode que implementarem per eliminar un usuari
    
    public Proveidor cercarProveidor(Proveidor proveidor); //Mètode que implementarem per cercar un usuari
    
    public List<Proveidor> cercarProveidorByCif(String nom); //Mètode que implementarem per cercar un usuari
}
