package Pi.Spring.MongoRepo;

import Pi.Spring.Entity.Rapport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("RapportMongoRepository")
public interface RapportMongoRepo extends MongoRepository<Rapport, Long> {
}
