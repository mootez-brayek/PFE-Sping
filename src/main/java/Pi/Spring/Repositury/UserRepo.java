package Pi.Spring.Repositury;

import Pi.Spring.Entity.Role;
import Pi.Spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.role =:role WHERE u.idUser =:idUser")
    void updateUserRole(@Param("role") Role role,@Param("idUser") Long idUser);
}
