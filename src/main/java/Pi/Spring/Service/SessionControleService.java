package Pi.Spring.Service;

import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;

import java.util.List;

public interface SessionControleService {

  public SessionControle AddSession(SessionControle session, List<String>usernames, List<String>names, List<String> descriptions);

     public SessionControle getSession (Long idSession);

    public List<SessionControle> getAllSession ();



    public void deleteSessionControle(Long idSessionControle);

    public SessionControle updateSessionControle(Long idSessionControle, SessionControle updatedSession, List<String>usernames, List<String>contribuablenames);

    public void asssignControlleur(long idSession,  List<String> usernames);
}
