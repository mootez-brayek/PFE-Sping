package Pi.Spring.Repositury;

import Pi.Spring.Entity.Impot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImpotRepo extends JpaRepository<Impot, Long> {
}
