package Pi.Spring.Service;


import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;
import Pi.Spring.Repositury.ContribuableRepo;
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


    @Override
    public SessionControle AddSession(SessionControle session,List<String>usernames,List<String>names) {
        List<User> userNames= new ArrayList<>();
        List<User>users = userRepo.findByUsernameIn(usernames);

        List<Contribuable> contribuableNames= new ArrayList<>();
        List<Contribuable> contribuables = contribuableRepo.findByNomIn(names);

        userNames.addAll(users);
        contribuableNames.addAll(contribuables);
        session.setContribuablesSession(contribuableNames);
        session.setControlleurs(userNames);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate.parse(session.getDate_Debut(), formatter);

        return sessionControleRepo.save(session);
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
    public SessionControle updateSessionControle(Long idSessionControle) {
        SessionControle sessionControle = sessionControleRepo.findById(idSessionControle).orElse(null);
        return sessionControleRepo.save(sessionControle);
    }

    @Override
    public void asssignControlleur(long idSession,  List<String> usernames) {
           SessionControle sessionControle = sessionControleRepo.findById(idSession).orElse(null);
           List<User> users= userRepo.findByUsernameIn(usernames);
           sessionControle.getControlleurs().addAll(users);
           sessionControleRepo.save(sessionControle);
       }



}
