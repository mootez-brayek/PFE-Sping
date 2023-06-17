package Pi.Spring.Repositury;

import Pi.Spring.Entity.Contribuable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ContribuableRepo extends JpaRepository<Contribuable, Long> {
    List<Contribuable> findByNomIn(List<String>names);
    Contribuable findByNom(String nom);
}
