package Pi.Spring.Service;

import Pi.Spring.Entity.Role;
import Pi.Spring.Entity.User;

import java.util.List;

public interface UserService {


    public User saveUser(User user);
    public List<User> getUsers();

    public void deleteUser(Long idUser);

    public User updateUSer(Long idUser);

    public User getUser(Long idUser);

    public void changeUserRole(Long idUser, Role newRole);
}
