package Pi.Spring.Controller;



import Pi.Spring.Entity.Role;
import Pi.Spring.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Role/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/save")
    public Role save(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @PostMapping("/affectRoleToUser/{username}/{libelle}")
    public void affectRoleToUser(@PathVariable String username, @PathVariable String libelle) {
         roleService.affectRoleToUser(username,libelle);
    }

    @GetMapping("/getAll")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}
