package Pi.Spring.Service;

import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.SessionControle;

import java.util.List;

public interface SessionControleService {

    public SessionControle AddSession(SessionControle session);

    public SessionControle getSession (Long idSession);

    public List<SessionControle> getAllSession ();



    public void deleteSessionControle(Long idSessionControle);

    public SessionControle updateSessionControle(Long idSessionControle);
}
