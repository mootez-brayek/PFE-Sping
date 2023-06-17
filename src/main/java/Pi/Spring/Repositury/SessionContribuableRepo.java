package Pi.Spring.Repositury;

import Pi.Spring.Entity.SessionContribuable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionContribuableRepo extends JpaRepository<SessionContribuable, Long> {
}
