package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Proveidor;
import java.util.List;

/**
 *
 * @author adria
 */

//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface ProveidorServiceInterface {
    
    public List<Proveidor> llistarProveidors(); //Mètode que implementarem per llistar productes
    
    public void afegirProveidor(Proveidor proveidor); //Mètode que implementarem per afegir un producte
    
    public void eliminarProveidor(Proveidor proveidor); //Mètode que implementarem per eliminar un producte
    
    public Proveidor cercarProveidor(Proveidor proveidor); //Mètode que implementarem per cercar un producte
}
