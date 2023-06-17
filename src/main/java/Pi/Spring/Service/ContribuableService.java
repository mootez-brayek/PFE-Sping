package Pi.Spring.Service;



import Pi.Spring.Entity.Contribuable;

import java.util.List;

public interface ContribuableService {

   public Contribuable saveContribuable(Contribuable contribuable);

    public List<Contribuable> getAllContribuables();

    public void deleteContribuable(Long idContribuable);

    public Contribuable updateContribuable(Long idContribuable, Contribuable updatedContribuable);

    public Contribuable getContribuable(Long idContribuable);

    public long getContribuableCount();

}
