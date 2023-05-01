package Pi.Spring.Service;

import Pi.Spring.Entity.Role;
import Pi.Spring.Entity.User;
import Pi.Spring.Repositury.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long idUser) {
        userRepo.deleteById(idUser);
    }

    @Override
    public User updateUSer(Long idUser) {
        var user= userRepo.findById(idUser).orElse(null);
        return userRepo.save(user);
    }

    @Override
    public User getUser(Long idUser) {
        return userRepo.findById(idUser).orElse(null);
    }

    @Override
    public void changeUserRole(Long idUser, Role newRole) {
        Optional<User> userOptional = userRepo.findById(idUser);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(newRole);
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found with id " + idUser);
        }
    }


}
