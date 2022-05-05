package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Categoria;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */

//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface CategoriaServiceInterface {
    
    public List<Categoria> llistarCategories(); //Mètode que implementarem per llistar categories
    
    public void crearCategoria(Categoria categoria); //Mètode que implementarem per afegir una categoria
    
    public void eliminarCategoria(Categoria categoria); //Mètode que implementarem per eliminar una categoria
    
    public Categoria cercarCategoria(Categoria categoria); //Mètode que implementarem per cercar una categoria
    
    public String rolUsername(User username);
    
    public Categoria findByName (String categoria); //Mètode que implementarem per cercar una categoria per nom
    
    public void editarCategoria (Categoria categoria, String newName); //Mètode que canvia el nom de la categoria
}
