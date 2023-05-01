package Pi.Spring.Entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "idUser")
public class Agent extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String agentId;
    public String Secteur;
    public Date Date_debut;
    public Date Date_fin;


    public Agent() {
        this.setRole(Role.Controlleur);
    }


    @ManyToMany(mappedBy="agentsSession",cascade = CascadeType.ALL)
    private List<SessionControle> sessionControles;

}
