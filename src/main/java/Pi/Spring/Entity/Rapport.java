package Pi.Spring.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rapport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRapport;
    private Date date;
    private String objet;
    private String Contenu;
    @Enumerated(EnumType.STRING)
    private Element elemnt;



    @ManyToOne
    SessionControle rapportSessionControle;
}
