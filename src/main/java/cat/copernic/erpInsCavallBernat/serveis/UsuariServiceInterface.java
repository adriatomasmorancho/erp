package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Usuari;
import java.util.List;

/**
 *
 * @author ivan
 */

//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface UsuariServiceInterface {
    
    public List<Usuari> llistarUsuaris(); //Mètode que implementarem per llistar usuaris
    
    public void crearUsuari(Usuari usuari); //Mètode que implementarem per afegir un usuari
    
    public void eliminarUsuari(Usuari usuari); //Mètode que implementarem per eliminar un usuari
    
    public Usuari cercarUsuari(Usuari usuari); //Mètode que implementarem per cercar un usuari
    
    public List<Usuari> cercarUsuariPerNom(String nom); //Mètode que implementarem per cercar un usuari
}