package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Categoria;
import java.util.List;

/**
 *
 * @author adria
 */

//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface CategoriaServiceInterface {
    
    public List<Categoria> llistarCategories(); //Mètode que implementarem per llistar productes
    
    public void crearCategoria(Categoria categoria); //Mètode que implementarem per afegir un gos
    
    public void eliminarCategoria(Categoria categoria); //Mètode que implementarem per eliminar un producte
    
    public Categoria cercarCategoria(Categoria categoria); //Mètode que implementarem per cercar un producte
}
