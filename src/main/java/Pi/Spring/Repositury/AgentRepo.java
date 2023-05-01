package Pi.Spring.Repositury;

import Pi.Spring.Entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepo extends JpaRepository<Agent,Long> {
}
