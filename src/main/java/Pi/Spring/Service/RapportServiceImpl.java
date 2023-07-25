package Pi.Spring.Service;


import Pi.Spring.Entity.*;
import Pi.Spring.MongoRepo.RapportMongoRepo;
import Pi.Spring.Repositury.RapportJpaRepository;
import Pi.Spring.Repositury.SessionControleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RapportServiceImpl implements RapportService {

    @Autowired
    public RapportJpaRepository rapportJpaRepository;

    @Autowired
    RapportMongoRepo rapportMongoRepo;

    @Autowired
    public SessionControleRepo sessionControleRepo;

    @Override
    public void generateReportIfNeeded(Programme programme) {
        log.info("Generating report if needed for Programme with ID: {}", programme.getIdProgramme());

        Rapport rapport = new Rapport();
        User controlleur = programme.getControlleur();
        String controlleurInfo = controlleur.getName() + " " +controlleur.getPrenom();

        rapport.setObjet(programme.getSessionContribuable().getSession().getObjet());
        rapport.setDate(LocalDate.now());
        rapport.setIdProgramme(programme.getIdProgramme());
        rapport.setControlleur(controlleurInfo);
        rapport.setContribuable(programme.getSessionContribuable().getContribuable().getNom());
        rapport.setContenu(generateReportContent(programme.getTasks()));
        rapportMongoRepo.save(rapport);
    }

    private String generateReportContent(List<Task> tasks) {
        StringBuilder contentBuilder = new StringBuilder();
        for (Task task : tasks) {
            LocalDate taskDate = task.getDate();
            String taskDescription = task.getDescription();
            String formattedTask = String.format("%s: %s%n", taskDate, taskDescription);
            contentBuilder.append(formattedTask);
        }
        return contentBuilder.toString();
    }

    @Override
    public Rapport getRapport(Long idRapport) {
        log.info("Getting Rapport with ID: {}", idRapport);
        return rapportMongoRepo.findById(idRapport).orElse(null);
    }

    @Override
    public List<Rapport> getAllRapport() {
        log.info("Getting all Rapports");
        return rapportMongoRepo.findAll();
    }

    @Override
    public void deleteRapport(Long idRapport) {
        log.info("Deleting Rapport with ID: {}", idRapport);
        Rapport rapport = rapportMongoRepo.findById(idRapport).orElse(null);
        rapportMongoRepo.delete(rapport);
    }

    @Override
    public Rapport updateRapport(Long idRapport) {
        log.info("Updating Rapport with ID: {}", idRapport);
        Rapport rapport= rapportMongoRepo.findById(idRapport).orElse(null);
        return rapportMongoRepo.save(rapport);
    }


}
