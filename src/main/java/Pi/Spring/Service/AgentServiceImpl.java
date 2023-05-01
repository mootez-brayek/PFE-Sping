package Pi.Spring.Service;

import Pi.Spring.Entity.Agent;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Repositury.AgentRepo;
import Pi.Spring.Repositury.SessionControleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService{

    @Autowired
    public AgentRepo agentRepo;
    @Autowired
    public SessionControleRepo sessionControleRepo;




    @Override
    public void addAndAffectAgent(Long idAgent, Long idSessionControle) {
        /*List<Agent>agents= new ArrayList<>();
        Agent agent= agentRepo.findById(idAgent).orElse(null);
        SessionControle sessionControle= sessionControleRepo.findById(idSessionControle).orElse(null);
        agents.add(agent);
        sessionControle.setAgents(agents);*/
    }

    @Override
    public List<Agent> getAllAgent() {
        return null;
    }

    @Override
    public void deleteAgent(Long idAgent) {

    }

    @Override
    public Agent updateAgent(Long idAgent) {
        return null;
    }

    @Override
    public Agent getAgent(Long idAgent) {
        return null;
    }
}
