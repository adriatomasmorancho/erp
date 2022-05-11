package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Producte;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cat.copernic.erpInsCavallBernat.DAO.ProducteDAO;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
*/
@Service ("productesDetailsService")
@Slf4j
public class ProducteService implements ProducteServiceInterface{
    
    /*Quan treballem en la capa de servei amb classes de tipus DAO, com és el cas, estem
     *treballant amb transaccions SQL, és a dir, quan fem una consulta a la BBDD, si aquesta
     *ha estat un èxit, el sistema ha de fer un COMMIT, en cas contrari un ROLLBACK. Així doncs,
     *mitjançant anotacions, hem d'indicar al sistema de quin tipus de transacció és cadascun
     *dels mètodes per accedir a la BBDD que implementem en aquesta classe.    
    */
    
    /*Atribut que defineix un producteDAO. Mitjançant aquest atribut el control ja no 
     *accedirà directament a la capa de dades, si no que accedirà mitjançant la capa de servei.
    */
    @Autowired
    private ProducteDAO producte; 
    
    @Autowired
    private UsuariServiceInterface usuariService;

    /*LListar productes de la taula producte de la BBDD erp*/
    @Override
    @Transactional(readOnly=true) 
    public List<Producte> llistarProductes() {
        List<Producte> myList = (List<Producte>) producte.findAll(); 
        List<Producte> myNewList = new ArrayList<>();
        
        for(Producte p : myList){
            if(p.getEstat()){
                myNewList.add(p);
            }
        }
        
        return myNewList;
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Producte> llistarAllProductes() {
        return (List<Producte>) producte.findAll(); 
    }
    

    /*Afegir el producte passat per paràmetre a la taula producte de la BBDD erp*/
    @Override
    @Transactional
    public void crearProducte(Producte producte) {
        
        /*Cridem al mètode save() de CrudRepository perquè afegeixi el producte passat com a paràmetre,
         *a la taula producte de la BBDD erp.
        */
        this.producte.save(producte); 
    }

    /*Eliminar el producte passat per paràmetre de la taula producte de la BBDD erp*/
    @Override
    @Transactional //Igual que en el mètode afegirProducte, modifiquem la informació de la BBDD
    public void eliminarProducte(Producte producte) {
        
        /*Cridem al mètode delete() de CrudRepository perquè elimini el producte passat com a paràmetre,
         *de la taula producte de la BBDD erp.
        */
        this.producte.delete(producte);
        
    }

    /*Cercar el producte passat per paràmetre en la taula producte de la BBDD erp*/
    @Override
    @Transactional(readOnly=true) //Igual que en el mètode llistarProductes, no modifiquem la informació de la BBDD
    public Producte cercarProducte(Producte producte) {
        
        /*Cridem al mètode findById() de CrudRepository perquè ens retorni el producte passat com a paràmetre.
         *El paràmetre que li passem a aquest mètode, ha de ser la clau primària de l'entitat, en el nostre 
         *cas el producte.
         *
         *Si el producte no existei retornarà null (orElse(null)).
        */ 

        return this.producte.findById(producte.getId_Producte()).orElse(null);
        
    }
    
    @Override
    public String rolUsername(User username) {
        var usuari = username.getUsername();
        var usuaris = usuariService.llistarUsuaris();
        String rol = null;
        for (var usuario : usuaris) {
            if (usuario.getUsername().equals(usuari)) {
                rol = usuario.getRol();
            }
        }
        return rol;
    }
    
    @Override
    public void activarProducte(Producte producte) {
        Producte myProd = cercarProducte(producte);
        myProd.setEstat(true);
        this.producte.save(myProd);
    }

    @Override
    public void desactivarProducte(Producte producte) {
        Producte myProd = cercarProducte(producte);
        myProd.setEstat(false);
        this.producte.save(myProd);
    }
    
}