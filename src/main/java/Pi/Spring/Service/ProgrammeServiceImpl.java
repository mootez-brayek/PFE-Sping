package Pi.Spring.Service;

import Pi.Spring.Entity.*;
import Pi.Spring.Repositury.ProgrammeRepo;
import Pi.Spring.Repositury.SessionControleRepo;
import Pi.Spring.Repositury.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProgrammeServiceImpl implements ProgrammeService{

    @Autowired
    ProgrammeRepo programmeRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    SessionControleRepo sessionControleRepo;

    @Override
    public Programme createProgramme(Programme programme, Long idSession, List<String> usernames, List<String>names) {

        var session = sessionControleRepo.findById(idSession).orElse(null);
        List<User> controlleurs = session.getControlleurs();
        List<SessionContribuable> sessionContribuables = session.getContribuablesSession();

        List<Contribuable> contribuables = sessionContribuables.stream()
                .map(SessionContribuable::getContribuable)
                .collect(Collectors.toList());
        return null;
    }
}
