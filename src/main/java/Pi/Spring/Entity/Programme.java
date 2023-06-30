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
public class Programme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProgramme;
    private Date date_debut;
    private Date date_fin;


    @ManyToOne
    @JoinColumn(name = "session_contribuable_id")
    private SessionContribuable sessionContribuable;

    @ManyToOne
    @JoinColumn(name = "controlleur_id")
    private User controlleur;



}
