package Pi.Spring.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private String date_Debut;
    private LocalDate date_Fin;
    private String Objet;
    private LocalDate date_Validation;
    @Enumerated(EnumType.STRING)
    private Etat etat;






    @ManyToMany(cascade=CascadeType.ALL)
    private List<Contribuable> contribuablesSession;


    @ManyToOne(cascade=CascadeType.ALL)
    private User admin;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="rapportSessionControle")
    List<Rapport>rapports;


    @ManyToMany
    private List<User> controlleurs;


}
