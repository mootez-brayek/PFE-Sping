package Pi.Spring.Service;

import Pi.Spring.Entity.Programme;
import Pi.Spring.Entity.Rapport;
import Pi.Spring.Entity.Task;


import java.util.List;

public interface ProgrammeService {
    public Programme createProgramme(Programme programme, Long idSession, Long idControlleur,Long idSessionContribuable, List<String> taskGoals);

    public List<Programme> getProgrammes();

    List<Programme> getProgrammesBySession(Long sessionId);

    List<Programme> getProgrammesByControlleur(String username);


    List<Task> getTasksByIdProgramme(Long idProgramme);

    public List<Rapport> getRapportsByProgramme(Long idProgramme);



    public int updateOverallProgress(Long idProgramme);

    public long getProgrammeCount();


}
