package Pi.Spring.MongoRepo;

import Pi.Spring.Entity.Rapport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RapportMongoRepository")
public interface RapportMongoRepo extends MongoRepository<Rapport, Long> {
    List<Rapport> findRapportByIdProgramme(Long idProgramme);
}
