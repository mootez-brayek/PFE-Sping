package Pi.Spring.Repositury;

import Pi.Spring.Entity.SessionControle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SessionControleRepo extends JpaRepository<SessionControle, Long> {

    List<SessionControle> findByCodeContainingIgnoreCase(String query);
}
