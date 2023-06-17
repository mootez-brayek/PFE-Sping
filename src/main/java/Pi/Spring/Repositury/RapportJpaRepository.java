package Pi.Spring.Repositury;

import Pi.Spring.Entity.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("RapportJpaRepository")
public interface RapportJpaRepository extends JpaRepository<Rapport,Long> {
}
