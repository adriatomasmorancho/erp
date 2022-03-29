package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;

import java.util.List;

/**
 *
 * @author adria
 */

//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface ComandaProfessorServiceInterface {
    
    public List<ComandaProfessor> llistarComandesProfessor(); //Mètode que implementarem per llistar productes
   
    public void crearComandaProfessor(ComandaProfessor comandaProfessor); //Mètode que implementarem per afegir un gos
    
    public void eliminarComandaProfessor(ComandaProfessor comandaProfessor); //Mètode que implementarem per eliminar un producte
    
    public ComandaProfessor cercarComandaProfessor(ComandaProfessor comandaProfessor); //Mètode que implementarem per cercar un producte

  
}
