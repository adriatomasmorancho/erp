/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.model;

/**
 *
 * @author rpuig
 */
public class crearCentralitzada {
    private ComandaAdministrador ca;
    private ComandaProfessor cp;

    public crearCentralitzada(ComandaAdministrador ca, ComandaProfessor cp) {
        this.ca = ca;
        this.cp = cp;
    }
    
    public crearCentralitzada()
    {
    }

    public ComandaAdministrador getCa() {
        return ca;
    }

    public void setCa(ComandaAdministrador ca) {
        this.ca = ca;
    }

    public ComandaProfessor getCp() {
        return cp;
    }

    public void setCp(ComandaProfessor cp) {
        this.cp = cp;
    }
}
