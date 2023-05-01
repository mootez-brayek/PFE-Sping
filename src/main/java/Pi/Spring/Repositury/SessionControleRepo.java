package Pi.Spring.Repositury;

import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SessionControleRepo extends JpaRepository<SessionControle, Long> {
}
