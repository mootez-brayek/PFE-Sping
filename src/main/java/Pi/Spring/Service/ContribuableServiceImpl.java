package Pi.Spring.Service;

import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Repositury.ContribuableRepo;
import Pi.Spring.Repositury.SessionControleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service @RequiredArgsConstructor
@Transactional
@Slf4j
public class ContribuableServiceImpl implements ContribuableService{

    @Autowired
    public ContribuableRepo contribuableRepo;
    @Autowired
    public SessionControleRepo sessionControleRepo;


    @Override
    public Contribuable saveContribuable(Contribuable contribuable) {
        return contribuableRepo.save(contribuable);
    }

    @Override
    public List<Contribuable> getAllContribuables() {
        return contribuableRepo.findAll();
    }


    @Override
    public void deleteContribuable(Long idContribuable) {
        Contribuable contribuable = contribuableRepo.findById(idContribuable).orElse(null);
        contribuableRepo.delete(contribuable);
    }

    @Override
    public Contribuable updateContribuable(Long idContribuable) {
        Contribuable contribuable = contribuableRepo.findById(idContribuable).orElse(null);
        return contribuableRepo.save(contribuable);
    }

    @Override
    public Contribuable getContribuable(Long idContribuable) {
        return contribuableRepo.findById(idContribuable).orElse(null);
    }

    @Override
    public void addAndAffectContribuable(Long idContribuable, Long idSessionControle) {
        List<Contribuable>contribuables= new ArrayList<>();
        SessionControle sessionControle = sessionControleRepo.findById(idSessionControle).orElse(null);
        Contribuable contribuable= contribuableRepo.findById(idContribuable).orElse(null);
        contribuables.add(contribuable);
        sessionControle.setContribuablesSession(contribuables);
    }
}
