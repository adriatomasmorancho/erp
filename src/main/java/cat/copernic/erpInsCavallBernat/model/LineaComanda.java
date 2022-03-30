package cat.copernic.erpInsCavallBernat.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


/**
 *
 * @author ivan
 */
@Data
@Entity
@Table(name = "lineacomanda")
public class LineaComanda implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @Id //Indica al sistema que l'atribut id_Producte és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generarem l'id
    private long id_ComandaProfessor;
    /*Validació per comprovar que el nom no està buit. Com a paràmetre li passem el missatge
     *que volem que aparegui.
     */
    
    @NotNull
    private long  id_producte;
    
    @NotNull
    private long  quantitat;
    
    @NotEmpty
    private long observacio;
    
    @NotEmpty
    private String  pre_elavoracions;
  
}

