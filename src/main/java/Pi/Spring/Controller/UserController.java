package Pi.Spring.Controller;

import Pi.Spring.Auth.AuthenticationResponse;
import Pi.Spring.Auth.AuthenticationService;
import Pi.Spring.Auth.RegisterRequest;
import Pi.Spring.Entity.User;
import Pi.Spring.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/add")
    public ResponseEntity<AuthenticationResponse> addUser(
            @RequestBody RegisterRequest request,  @RequestParam("libelle") String libelle) {
        return ResponseEntity.ok(authenticationService.addUser(request,libelle));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request, HttpServletResponse response
       ) throws IOException {
        authenticationService.refreshToken(request,response);
  ;
    }

    @GetMapping("/get")
    public List<User> getUsers(){
        return userService.getUsers();
    }


    @PutMapping("/updateRole/{idUser}/{idRole}")

    public ResponseEntity<?> changeUserRole(@PathVariable Long idUser, @PathVariable Long idRole) {
        userService.changeUserRole(idUser, idRole);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/assign/{username}/{libelle}")

    public ResponseEntity<?> assignRoleToUser(@PathVariable String username, @PathVariable String libelle) {
        userService.assignRoleToUser(username,libelle);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }



}