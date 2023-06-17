package Pi.Spring.Service;


import Pi.Spring.Entity.*;
import Pi.Spring.Repositury.ContribuableRepo;
import Pi.Spring.Repositury.SessionContribuableRepo;
import Pi.Spring.Repositury.SessionControleRepo;
import Pi.Spring.Repositury.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SessionControleServiceImpl implements SessionControleService{

    @Autowired
    private SessionControleRepo sessionControleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ContribuableRepo contribuableRepo;
    @Autowired
    SessionContribuableRepo sessionContribuableRepo;


    @Override
    public SessionControle AddSession(SessionControle session,List<String>usernames,List<String>names, List<String> descriptions) {
        List<User> users=new ArrayList<>();
        List<User> controlleurs = userRepo.findByUsernameIn(usernames);
        users.addAll(controlleurs);
        List<Contribuable> contribuables = contribuableRepo.findByNomIn(names);

        // Make sure the number of descriptions matches the number of contribuables
        if (descriptions.size() != contribuables.size()) {
            throw new IllegalArgumentException("Number of descriptions doesn't match the number of contribuables");
        }

        List<SessionContribuable> sessionContribuables = new ArrayList<>();

        // Create SessionContribuable entities for each Contribuable with the corresponding description
        for (int i = 0; i < contribuables.size(); i++) {
            Contribuable contribuable = contribuables.get(i);
            User user = users.get(i);
            String description = descriptions.get(i);

            SessionContribuable sessionContribuable = new SessionContribuable();
            sessionContribuable.setSession(session);
            sessionContribuable.setContribuable(contribuable);
            sessionContribuable.setDescription(description);
            sessionContribuables.add(sessionContribuable);
            sessionContribuableRepo.save(sessionContribuable);
        }
        session.setControlleurs(users);
        session.setEtat(Etat.EnAttent);

        // Set the sessionContribuables for the session
        session.setContribuablesSession(sessionContribuables);
        return sessionControleRepo.save(session);
    }

    @Override
    public SessionControle updateSessionControle(Long idSessionControle, SessionControle updatedSession,List<String>usernames,List<String>contribuablenames) {
          /*  SessionControle sessionControle = sessionControleRepo.findById(idSessionControle).orElse(null);
            sessionControle.setObjet(updatedSession.getObjet());
        if (usernames != null) {
            sessionControle.getControlleurs().clear();
            List<User> userNames = new ArrayList<>();
            List<User> users = userRepo.findByUsernameIn(usernames);
            userNames.addAll(users);
            sessionControle.setControlleurs(userNames);
        }
        if (contribuablenames != null) {
            sessionControle.getContribuablesSession().clear();
            List<Contribuable> contribuableNames = new ArrayList<>();
            List<Contribuable> contribuables = contribuableRepo.findByNomIn(contribuablenames);
            contribuableNames.addAll(contribuables);
            sessionControle.setContribuablesSession(contribuables);
        }
        if (updatedSession.getObjet() != null) {
            sessionControle.setObjet(updatedSession.getObjet());
        }*/
        return sessionControleRepo.save(updatedSession);
    }
    @Override
    public SessionControle getSession(Long idSession) {
        return sessionControleRepo.findById(idSession).orElse(null);
    }

    @Override
    public List<SessionControle> getAllSession() {
        return sessionControleRepo.findAll();
    }

    @Override
    public void deleteSessionControle(Long idSessionControle) {
        SessionControle sessionControle = sessionControleRepo.findById(idSessionControle).orElse(null);
        sessionControleRepo.delete(sessionControle);
    }



    @Override
    public void asssignControlleur(long idSession,  List<String> usernames) {
           SessionControle sessionControle = sessionControleRepo.findById(idSession).orElse(null);
           List<User> users= userRepo.findByUsernameIn(usernames);
           sessionControle.getControlleurs().addAll(users);
           sessionControleRepo.save(sessionControle);
       }



}
