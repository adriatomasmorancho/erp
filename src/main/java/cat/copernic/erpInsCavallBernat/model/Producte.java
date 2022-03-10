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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author ivan
 */
@Data
@Entity
@Table(name = "producte")
public class Producte implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @Id //Indica al sistema que l'atribut idgos és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generarem l'id

    private long id;

    private long idproducte;


    /*Validació per comprovar que el nom no està buit. Com a paràmetre li passem el missatge
     *que volem que aparegui.
     */
    @NotEmpty
    @Size(min = 5) //Validem un nombre mínim de caràcters
    private String nom;

    /*Validació per comprovar que el sexe no està buit. Com a paràmetre no li passem res, per tant
     *ens mostrarà el missatge per defecte del sitema.
     */
    @NotEmpty
    private String sexe;

    @Min(value = 0) //Validació que el valor de l'edat no sigui negatiu
    private int edat;

}
