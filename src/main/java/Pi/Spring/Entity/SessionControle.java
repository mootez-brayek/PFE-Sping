package Pi.Spring.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionControle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSession;
    private Date date_Debut;
    private Date date_Fin;
    private String Objet;
    private Date date_Validation;
    @Enumerated(EnumType.STRING)
    private Etat etat;



    @ManyToMany(cascade=CascadeType.ALL)
    private List<Contribuable> contribuablesSession;


    @ManyToMany(cascade=CascadeType.ALL)
    private List<Agent> agentsSession;

    @ManyToOne(cascade=CascadeType.ALL)
    private User admin;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="rapportSessionControle")
    List<Rapport>rapports;


}
