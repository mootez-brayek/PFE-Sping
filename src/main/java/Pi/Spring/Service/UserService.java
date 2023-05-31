package Pi.Spring.Service;

import Pi.Spring.Entity.User;

import java.util.List;

public interface UserService {


    public List<User> getUsers();

    public void deleteUser(Long idUser);

    public User updateUSer(Long idUser);

    public User getUser(Long idUser);

    public void changeUserRole(Long idUser, Long idRole);

    public void assignRoleToUser(String username, String libelle);


    public String getUserRole(String  userName);

}
