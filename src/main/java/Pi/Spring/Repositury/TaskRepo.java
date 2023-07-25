package Pi.Spring.Repositury;

import Pi.Spring.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TaskRepo extends JpaRepository<Task,Long> {
}
