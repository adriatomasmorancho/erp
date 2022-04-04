package cat.copernic.erpInsCavallBernat.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


/**
 *
 * @author adria
 */
@Data
@Entity
@Table(name = "linea_comanda")
public class LineaComanda implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @Id //Indica al sistema que l'atribut id_comanda és la clau primària de la BBDD
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_linea_comanda", referencedColumnName = "id_comanda")
    private ComandaProfessor id_linea_comanda;
    // private long id_linea_comanda;
    /*Validació per comprovar que el nom no està buit. Com a paràmetre li passem el missatge
     *que volem que aparegui.
     */
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producte", referencedColumnName = "id_producte")
    private Producte  id_Producte;
    
    @NotNull
    private long  quantitat;
    
    @NotEmpty
    private long observacio;
    
    @NotEmpty
    private String  pre_elavoracions;
  
}

