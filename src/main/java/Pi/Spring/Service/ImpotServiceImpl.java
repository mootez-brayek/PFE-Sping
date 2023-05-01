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
        return impotRepo.save(impot);
    }

    @Override
    public List<Impot> getAllImpots() {
        return impotRepo.findAll();
    }

    @Override
    public void deleteImpot(Long idImpot) {
        Impot impot = impotRepo.findById(idImpot).orElse(null);
        impotRepo.delete(impot);

    }

    @Override
    public Impot updateImpot(Long idImpot) {
        Impot impot = impotRepo.findById(idImpot).orElse(null);
        return impotRepo.save(impot);
    }

    @Override
    public Impot getImpot(Long idImpot) {
        return impotRepo.findById(idImpot).orElse(null);
    }

     @Override
   public void addAndAffectImpots(Impot impot, Long idContribuable) {
     /*   Contribuable contribuable = contribuableRepo.findById(idContribuable).orElse(null);
        impot.setImpotContibuable(contribuable);
        impotRepo.save(impot);*/
    }
}
