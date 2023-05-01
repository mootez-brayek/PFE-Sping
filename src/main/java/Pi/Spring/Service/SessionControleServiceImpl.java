package Pi.Spring.Service;


import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Repositury.SessionControleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SessionControleServiceImpl implements SessionControleService{

    @Autowired
    private SessionControleRepo sessionControleRepo;


    @Override
    public SessionControle AddSession(SessionControle session) {
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


}
