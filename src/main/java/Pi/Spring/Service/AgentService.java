package Pi.Spring.Service;

import Pi.Spring.Entity.Agent;

import java.util.List;

public interface AgentService {

    public void addAndAffectAgent(Long idAgent,Long idSessionControle);

    public List<Agent> getAllAgent();

    public void deleteAgent(Long idAgent);

    public Agent updateAgent(Long idAgent);

    public Agent getAgent(Long idAgent);
}
