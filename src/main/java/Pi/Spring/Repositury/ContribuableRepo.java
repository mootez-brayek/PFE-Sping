package Pi.Spring.Repositury;

import Pi.Spring.Entity.Contribuable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContribuableRepo extends JpaRepository<Contribuable, Long> {
}
