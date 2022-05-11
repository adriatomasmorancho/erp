/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.DAO.ProveidorDAO;
import cat.copernic.erpInsCavallBernat.model.Proveidor;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rpuig
 */
@Service ("proveidorDetailsService")
@Slf4j
public class ProveidorService implements ProveidorServiceInterface {
    
    @Autowired
    private ProveidorDAO proveidorDAO;
    
    @Autowired
    private UsuariServiceInterface usuariService;

    @Override
    @Transactional(readOnly = true)
    public List<Proveidor> llistarProveidors() {        
        List<Proveidor> myList = (List<Proveidor>) proveidorDAO.findAll();
        List<Proveidor> myNewList = new ArrayList<>();
        
        for(Proveidor p : myList){
            if(p.getEstat()){
                myNewList.add(p);
            }
        }
        
        return myNewList;
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Proveidor> llistarAllProveidors() {
        return (List<Proveidor>) proveidorDAO.findAll(); 
    }

    @Override
    public void crearProveidor(Proveidor proveidor) {
        this.proveidorDAO.save(proveidor);
    }

    @Override
    public void eliminarProveidor(Proveidor proveidor) {
        this.proveidorDAO.delete(proveidor);
    }
    
    @Override
    public Proveidor cercarProveidor(Proveidor proveidor) {
        return this.proveidorDAO.findById(proveidor.getCif()).orElse(null);
    }

    @Override
    public List<Proveidor> cercarProveidorByCif(String nom) {
        return (List<Proveidor>) proveidorDAO.findByCif(nom);
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
    public void activarProveidor(Proveidor proveidor) {
        Proveidor myProv = cercarProveidor(proveidor);
        myProv.setEstat(true);
        proveidorDAO.save(myProv);
    }

    @Override
    public void desactivarProveidor(Proveidor proveidor) {
        Proveidor myProv = cercarProveidor(proveidor);
        myProv.setEstat(false);
        proveidorDAO.save(myProv);
    }

}
