package Pi.Spring.Service;

import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.Programme;
import Pi.Spring.Entity.Rapport;

import java.util.List;

public interface RapportService {

    public void generateReportIfNeeded(Programme programme);

    public Rapport getRapport (Long idRapport);


    public List<Rapport> getAllRapport ();

    public void deleteRapport(Long idRapport);

    public Rapport updateRapport(Long idRapport);


}
