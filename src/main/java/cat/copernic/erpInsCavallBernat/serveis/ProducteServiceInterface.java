package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Producte;
import java.util.List;

/**
 *
 * @author fta
 */

//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface ProducteServiceInterface {
    
    public List<Producte> llistarProductes(); //Mètode que implementarem per llistar gossos
    
    public void afegirProducte(Producte producte); //Mètode que implementarem per afegir un gos
    
    public void eliminarProducte(Producte producte); //Mètode que implementarem per eliminar un gos
    
    public Producte cercarProducte(Producte producte); //Mètode que implementarem per cercar un gos
}
