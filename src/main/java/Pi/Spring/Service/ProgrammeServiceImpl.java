package Pi.Spring.Service;

import Pi.Spring.Entity.*;
import Pi.Spring.Repositury.ProgrammeRepo;
import Pi.Spring.Repositury.SessionContribuableRepo;
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
    @Autowired
    SessionContribuableRepo sessionContribuableRepo;


    @Override
    public Programme createProgramme(Programme programme, Long idSession, Long idControlleur, Long idSessionContribuable) {
        SessionControle session = sessionControleRepo.findById(idSession).orElse(null);

        if (session != null) {
            User selectedControlleur = session.getControlleurs()
                    .stream()
                    .filter(controlleur -> controlleur.getIdUser().equals(idControlleur))
                    .findFirst()
                    .orElse(null);

            SessionContribuable selectedContribuable = session.getContribuablesSession()
                    .stream()
                    .filter(sessionContribuable -> sessionContribuable.getId().equals(idSessionContribuable))
                    .findFirst()
                    .orElse(null);

            if (selectedControlleur != null && selectedContribuable != null) {
                programme.setControlleur(selectedControlleur);
                programme.setSessionContribuable(selectedContribuable);
            }
        }

        return programmeRepo.save(programme);
    }

    @Override
    public List<Programme> getProgrammes() {
        return programmeRepo.findAll();
    }
}
