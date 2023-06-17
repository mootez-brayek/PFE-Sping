package Pi.Spring.Service;

import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.Programme;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;

import java.util.List;

public interface ProgrammeService {
    public Programme createProgramme(Programme programme, Long idSession, List<String> usernames, List<String>names);
}
