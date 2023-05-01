package Pi.Spring.Service;

import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.Rapport;

import java.util.List;

public interface RapportService {

    public Rapport AddRapport(Rapport Rapport);

    public Rapport getRapport (Long idRapport);

    public List<Rapport> getAllRapport ();

    public void deleteRapport(Long idRapport);

    public Rapport updateRapport(Long idRapport);


    public void addAndAffectRapport(Rapport rapport, Long idSessionControle);
}
