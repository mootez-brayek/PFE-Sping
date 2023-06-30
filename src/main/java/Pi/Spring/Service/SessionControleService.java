package Pi.Spring.Service;

import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.SessionContribuable;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;

import java.util.List;

public interface SessionControleService {

  public SessionControle AddSession(SessionControle session, List<String>usernames, List<String>names, List<String> descriptions);

     public List<SessionContribuable> getSessionContribuable (Long idSession);
     public List<User> getSessionControlleurs (Long idSession);
     public List<SessionControle> getAllSession ();
     public SessionControle getSession(Long idSession);

    public void deleteSessionControle(Long idSessionControle);

    public SessionControle updateSessionControle(Long idSessionControle, SessionControle updatedSession, List<String>usernames, List<String>contribuablenames);

    public long getSessionCount();

    public SessionControle validateSession(Long idSession);

;

}
