package Pi.Spring.Service;

import Pi.Spring.Entity.*;

import Pi.Spring.MongoRepo.RapportMongoRepo;
import Pi.Spring.Repositury.ProgrammeRepo;
import Pi.Spring.Repositury.SessionContribuableRepo;
import Pi.Spring.Repositury.SessionControleRepo;
import Pi.Spring.Repositury.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProgrammeServiceImpl implements ProgrammeService{

    private final JavaMailSender javaMailSender;
    @Autowired
    ProgrammeRepo programmeRepo;

    @Autowired
    RapportMongoRepo rapportMongoRepo;

    @Autowired
    UserRepo userRepo;
    @Autowired
    SessionControleRepo sessionControleRepo;

    @Autowired
    SessionContribuableRepo sessionContribuableRepo;

    @Autowired
    RapportService rapportService;


    @Override
    public Programme createProgramme(Programme programme, Long idSession, Long idControlleur, Long idSessionContribuable, List<String> taskGoals) {
        log.info("Creating new Programme");
        SessionControle session = sessionControleRepo.findById(idSession).orElse(null);

        if (session != null) {
            User selectedControlleur = session.getControlleurs()
                    .stream()
                    .filter(controlleur -> controlleur.getIdUser().equals(idControlleur))
                    .findFirst()
                    .orElse(null);

            SessionContribuable selectedContribuable = session.getContribuablesSession()
                    .stream()
                    .filter(sessionContribuable -> sessionContribuable.getId().equals(idSessionContribuable))
                    .findFirst()
                    .orElse(null);

            if (selectedControlleur != null && selectedContribuable != null) {
                programme.setControlleur(selectedControlleur);
                programme.setSessionContribuable(selectedContribuable);

                List<Task> tasks = new ArrayList<>();
                for (String goal : taskGoals) {
                    Task task = new Task();
                    task.setGoal(goal);
                    task.setProgramme(programme);
                    task.setAvancement(Avancement.aFaire);
                    tasks.add(task);
                }
                programme.setTasks(tasks);
                log.info("Email notification sent to the controlleur: {}", selectedControlleur.getEmail());
            } else {
                log.warn("Controlleur with ID {} or Contribuable with ID {} not found, cannot create Programme", idControlleur, idSessionContribuable);
            }
        } else {
            log.warn("Session with ID {} not found, cannot create Programme", idSession);
        }

        return programmeRepo.save(programme);
    }


    @Override
    public List<Programme> getProgrammes() {
        log.info("Getting all Programmes");
        return programmeRepo.findAll();
    }

    @Override
    public List<Programme> getProgrammesBySession(Long sessionId) {
        log.info("Getting Programmes by Session ID: {}", sessionId);
        return programmeRepo.getProgrammesBySession(sessionId);
    }

    @Override
    public List<Programme> getProgrammesByControlleur(String username) {
        log.info("Getting Programmes by Controlleur Username: {}", username);
        return programmeRepo.getProgrammesByControlleur(username);
    }


    @Override
    public List<Task> getTasksByIdProgramme(Long idProgramme){
        var programme = programmeRepo.findById(idProgramme).orElse(null);
        log.info("Getting Tasks by ProgrammeId: {}", idProgramme);
        return programme.getTasks();
    }
    @Override
    public int updateOverallProgress(Long idProgramme) {
        log.info("Updating Overall Progress for Programme with ID: {}", idProgramme);
        var programme = programmeRepo.findById(idProgramme).orElse(null);
        List<Task> tasks = programme.getTasks();

        if (tasks.isEmpty()) {
            log.warn("No tasks found for Programme with ID: {}", idProgramme);
            return 0;
        }

        int totalProgress = 0;
        for (Task task : tasks) {
            totalProgress += task.getProgress();
        }

        int overallProgress = totalProgress / tasks.size();
        programme.setOverallProgress(overallProgress);

        if (overallProgress == 100) {
            log.info("Overall Progress is 100% for Programme with ID: {}", idProgramme);
            rapportService.generateReportIfNeeded(programme);
        }

        return overallProgress;
    }
    @Override
    public List<Rapport> getRapportsByProgramme(Long idProgramme) {
        log.info("Getting Rapports by Programme ID: {}", idProgramme);
        return rapportMongoRepo.findRapportByIdProgramme(idProgramme);
    }
    @Override
    public long getProgrammeCount() {
        log.info("Getting Programme Count");
        return programmeRepo.count();
    }
}