package Pi.Spring.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Document(collection = "rapport")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rapport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long idRapport;
    private Long idProgramme;
    private LocalDate date;
    private String objet;
    private String contenu;
    private String contribuable;
    private Long SessionCode;
    private String controlleur;




    @OneToOne
    Programme programme;
}
