/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author rpuig
 */
@Data
@Entity
@Table(name="Proveidor")
public class Proveidor implements Serializable {
    private static final long serialVersionUID=1L;

    @Id //L'atribut idRol és la clau primària de la BBDD    
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String cif;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String nom;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String email;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String telefon;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String adreca;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String contacte;
}