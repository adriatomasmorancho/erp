/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Modul;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */
public interface ModulServiceInterface {
    
 public List<Modul> llistarModuls(); //Mètode que implementarem per llistar moduls
    
    public String getRolUserCurrent(User username);
    
    public void crearModul(Modul modul); //Mètode que implementarem per afegir un Modul
    
    public void eliminarModul(Modul modul); //Mètode que implementarem per eliminar un Modul
    
    public Modul cercarModul(Modul modul); //Mètode que implementarem per cercar un Modul
    
    public Modul findByName(String modul); //Mètode que implementarem per cercar un Modul
    
    public void editarModul(Modul modul, String NewName); //Mètode que implementarem per editar un Modul
   
    public String rolUsername(User username);
   
}

