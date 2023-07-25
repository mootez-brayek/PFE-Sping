package Pi.Spring.Repositury;

import Pi.Spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByUsernameIn(List<String> usernames);

    User findByEmail(String email);

    boolean existsByEmail(String email);


    List<User> findByUsernameContainingIgnoreCaseOrNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPrenomContainingIgnoreCase(String usernameQuery, String emailQuery,String nameQuery, String prenomQuery);





}
