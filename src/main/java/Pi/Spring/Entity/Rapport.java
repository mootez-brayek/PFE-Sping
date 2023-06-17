package Pi.Spring.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.io.Serializable;
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
    private String idRapport;
    private Date date;
    private String objet;
    private String Contenu;
    @Enumerated(EnumType.STRING)
    private Element elemnt;



    @ManyToOne
    SessionControle rapportSessionControle;
}
