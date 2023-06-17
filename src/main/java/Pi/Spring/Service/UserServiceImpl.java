package Pi.Spring.Service;

import Pi.Spring.Entity.Role;
import Pi.Spring.Entity.User;
import Pi.Spring.Repositury.RoleRepo;
import Pi.Spring.Repositury.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;


    @Override
    public List<User> getUsers() {
        List<User>users= userRepo.findAll();

        return users;
    }

    @Override
    public void deleteUser(Long idUser) {
        userRepo.deleteById(idUser);
    }

    @Override
    public User updateUSer(Long idUser,User updatedUser) {
        var user= userRepo.findById(idUser).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + idUser));
        if (updatedUser.getName() != null) {
            user.setName(updatedUser.getName());
        }
        if (updatedUser.getUsername() != null) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(updatedUser.getPassword());
        }
        return userRepo.save(user);
    }

    @Override
    public User getUser(Long idUser) {
        return userRepo.findById(idUser).orElse(null);
    }

    @Override
    public void changeUserRole(Long idUser, Long idRole) {
        Optional<User> userOptional = userRepo.findById(idUser);
        Optional<Role> roleOptional = roleRepo.findById(idRole);
        if (userOptional.isPresent() && roleOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleOptional.get();
            user.setRole(role);
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found with id " + idUser);
        }
    }

    @Override
    public void assignRoleToUser(String username, String libelle) {
        Optional<User> userOptional = userRepo.findByUsername(username);
        Optional<Role> roleOptional = roleRepo.findByLibelle(libelle);
        if (userOptional.isPresent() && roleOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleOptional.get();
            user.setRole(role);
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found with username " + username);
        }

    }

    @Override
    public String getUserRole(String userName) {
        Optional<User> userOptional = userRepo.findByUsername(userName);
        User user = userOptional.get();
        return  user.getRole().getLibelle();

    }

    @Override
    public long getUserCount() {
        return userRepo.count();
    }


}
