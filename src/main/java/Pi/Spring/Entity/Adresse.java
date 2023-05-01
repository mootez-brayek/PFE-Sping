package Pi.Spring.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAdresse;
    public String rue;
    public String pays;
    public String numero;




    @ManyToOne
    Contribuable contribuable;
}
