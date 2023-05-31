package Pi.Spring.Service;


import Pi.Spring.Entity.Role;
import Pi.Spring.Entity.User;
import Pi.Spring.Repositury.RoleRepo;
import Pi.Spring.Repositury.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public Role addRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void deleteRole(long idRole) {
        Role role = roleRepo.findById(idRole).orElse(null);
        roleRepo.delete(role);
    }

    @Override
    public void affectRoleToUser(String username, String libelle) {
        Role role= roleRepo.findByLibelle(libelle).orElse(null);
        User user= userRepo.findByUsername(username).orElse(null);
        user.setRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }
}
