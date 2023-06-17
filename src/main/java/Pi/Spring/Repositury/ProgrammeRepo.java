package Pi.Spring.Repositury;

import Pi.Spring.Entity.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammeRepo extends JpaRepository<Programme, Long> {
}
