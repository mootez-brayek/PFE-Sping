package Pi.Spring.Service;


import Pi.Spring.Entity.Rapport;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Repositury.RapportRepo;
import Pi.Spring.Repositury.SessionControleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RapportServiceImpl implements RapportService {

    @Autowired
    public RapportRepo rapportRepo;

    @Autowired
    public SessionControleRepo sessionControleRepo;

    @Override
    public Rapport AddRapport(Rapport Rapport) {
        return rapportRepo.save(Rapport);
    }

    @Override
    public Rapport getRapport(Long idRapport) {
        return rapportRepo.findById(idRapport).orElse(null);
    }

    @Override
    public List<Rapport> getAllRapport() {
        return rapportRepo.findAll();
    }

    @Override
    public void deleteRapport(Long idRapport) {
        Rapport rapport = rapportRepo.findById(idRapport).orElse(null);
        rapportRepo.delete(rapport);
    }

    @Override
    public Rapport updateRapport(Long idRapport) {
        Rapport rapport=rapportRepo.findById(idRapport).orElse(null);
        return rapportRepo.save(rapport);
    }

    @Override
    public void addAndAffectRapport(Rapport rapport, Long idSessionControle) {
        SessionControle sessionControle = sessionControleRepo.findById(idSessionControle).orElse(null);
        rapport.setRapportSessionControle(sessionControle);
    }
}
