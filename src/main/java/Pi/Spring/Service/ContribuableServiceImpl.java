package Pi.Spring.Service;

import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;
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
        contribuableRepo.deleteById(idContribuable);
    }

    @Override
    public Contribuable updateContribuable(Long idContribuable, Contribuable updatedContribuable) {
        var contribuable = contribuableRepo.findById(idContribuable).orElseThrow(() -> new IllegalArgumentException("Contribuable not found with ID: " + idContribuable));

        if (updatedContribuable.getFormeJuridique() != null) {
            contribuable.setFormeJuridique(updatedContribuable.getFormeJuridique());
        }

        if (updatedContribuable.getNom() != null) {
            contribuable.setNom(updatedContribuable.getNom());
        }

        if (updatedContribuable.getSecteurActivite() != null) {
            contribuable.setSecteurActivite(updatedContribuable.getSecteurActivite());
        }

        if (updatedContribuable.getSituationJuridique() != null) {
            contribuable.setSituationJuridique(updatedContribuable.getSituationJuridique());
        }
        return contribuableRepo.save(contribuable);
    }

    @Override
    public Contribuable getContribuable(Long idContribuable) {
        return contribuableRepo.findById(idContribuable).orElse(null);
    }

    @Override
    public long getContribuableCount() {
        return contribuableRepo.count();
    }


}
