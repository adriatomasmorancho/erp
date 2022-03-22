package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Producte;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cat.copernic.erpInsCavallBernat.DAO.ProducteDAO;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author fta
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
*/
@Service ("producteDetailsService")
@Slf4j
public class ProducteService implements ProducteServiceInterface{

    @Autowired
    private ProducteDAO producteDAO; 


    @Override
    @Transactional(readOnly=true) 
    public List<Producte> llistarProductes() {
        return (List<Producte>) producteDAO.findAll(); 
    }

    @Override
    @Transactional
    public void crearProducte(Producte producte) {
        this.producteDAO.save(producte); 
    }

    @Override
    @Transactional
    public void eliminarProducte(Producte producte) {
        this.producteDAO.delete(producte);
    }

    @Override
    @Transactional(readOnly=true)
    public Producte cercarProducte(Producte producte) {
        return this.producteDAO.findById(producte.getId_Producte()).orElse(null);
    }
    
}
