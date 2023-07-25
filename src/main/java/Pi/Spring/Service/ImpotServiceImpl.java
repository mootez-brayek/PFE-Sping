package Pi.Spring.Service;


import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.Impot;
import Pi.Spring.Repositury.ContribuableRepo;
import Pi.Spring.Repositury.ImpotRepo;
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
public class ImpotServiceImpl implements ImpotService{


    @Autowired
    private ImpotRepo impotRepo;

    @Autowired
    private ContribuableRepo contribuableRepo;


    @Override
    public Impot saveImpot(Impot impot) {
        log.info("Saving impot: {}", impot);
        return impotRepo.save(impot);
    }

    @Override
    public List<Impot> getAllImpots() {
        log.info("Getting all Impots");
        return impotRepo.findAll();
    }

    public void deleteImpot(Long idImpot) {
        log.info("Deleting Impot with ID: {}", idImpot);
        Impot impot = impotRepo.findById(idImpot).orElse(null);
        if (impot != null) {
            impotRepo.delete(impot);
            log.info("Impot with ID {} deleted successfully", idImpot);
        } else {
            log.warn("Impot with ID {} not found, no deletion performed", idImpot);
        }
    }

    @Override
    public Impot updateImpot(Long idImpot) {
        Impot impot = impotRepo.findById(idImpot).orElse(null);
        log.info("");
        return impotRepo.save(impot);
    }

    @Override
    public Impot getImpot(Long idImpot) {
        log.info("getting Impot with ID: {}", idImpot);
        return impotRepo.findById(idImpot).orElse(null);
    }

     @Override
   public void addAndAffectImpots(Impot impot, Long idContribuable) {
     /*   Contribuable contribuable = contribuableRepo.findById(idContribuable).orElse(null);
        impot.setImpotContibuable(contribuable);
        impotRepo.save(impot);*/
    }
}
