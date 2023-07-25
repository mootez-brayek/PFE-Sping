package Pi.Spring.Service;

import Pi.Spring.Entity.Avancement;
import Pi.Spring.Entity.Task;
import Pi.Spring.Repositury.ProgrammeRepo;
import Pi.Spring.Repositury.TaskRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private SessionControleService sessionControleService;



    @Override
    public int calculateProgress(Avancement avancement) {
        int progress;
        switch (avancement) {
            case aFaire:
                progress = 0;
                break;
            case enCours:
                progress = 50;
                break;
            case termine:
                progress = 100;
                break;
            default:
                progress = 0;
        }
        return progress;
    }

    @Override
    public Task setEnCours(Long idTask) {
        log.info("Setting Task with ID {} to 'enCours' state.", idTask);
        var task = taskRepo.findById(idTask).orElse(null);
        if (task == null) {
            log.warn("Task not found with ID: {}", idTask);
            return null;
        }

        task.setAvancement(Avancement.enCours);
        int progress = calculateProgress(task.getAvancement());
        task.setProgress(progress);
        return taskRepo.save(task);
    }

    @Override
    public Task getTask(Long idTask) {
        log.info("Getting Task with ID: {}", idTask);
        return taskRepo.findById(idTask).orElse(null);
    }

    @Override
    public Task updateTask(Long idTask, Task updatedTask) {
        log.info("Updating Task with ID: {}", idTask);
        var task = taskRepo.findById(idTask).orElse(null);
        if (task == null) {
            log.warn("Task not found with ID: {}", idTask);
            return null;
        }
        task.setDescription(updatedTask.getDescription());
        task.setAvancement(Avancement.enCours);
        task.setProgress(calculateProgress(task.getAvancement()));
        Long idProgramme =task.getProgramme().getIdProgramme();
        Long idSession=task.getProgramme().getSessionContribuable().getSession().getIdSession();
        if (idProgramme != null) {
            programmeService.updateOverallProgress(idProgramme);
            sessionControleService.updateProgress(idSession);
        }
        return taskRepo.save(task);
    }


    @Override
    public Task submitTask(Long idTask, Task updatedTask) {
        log.info("Submitting Task with ID: {}", idTask);
        var task = taskRepo.findById(idTask).orElse(null);
        if (task == null) {
            log.warn("Task not found with ID: {}", idTask);
            return null;
        }
        task.setDescription(updatedTask.getDescription());
        task.setAvancement(Avancement.termine);
        task.setDate(LocalDate.now());
        task.setProgress(calculateProgress(task.getAvancement()));
        Long idProgramme =task.getProgramme().getIdProgramme();
        Long idSession=task.getProgramme().getSessionContribuable().getSession().getIdSession();
        if (idProgramme != null) {
            programmeService.updateOverallProgress(idProgramme);
            sessionControleService.updateProgress(idSession);
        }
        return taskRepo.save(task);
    }
}
