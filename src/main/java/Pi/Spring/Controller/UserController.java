package Pi.Spring.Controller;

import Pi.Spring.Auth.AuthenticationRequest;
import Pi.Spring.Auth.AuthenticationResponse;
import Pi.Spring.Auth.AuthenticationService;
import Pi.Spring.Auth.RegisterRequest;
import Pi.Spring.Entity.Role;
import Pi.Spring.Entity.User;
import Pi.Spring.Service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/registerControlleur")
    public ResponseEntity<AuthenticationResponse> registerControlleur(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerControlleur(request));
    }


    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }
    @PostMapping("/registerSuperAdmin")
    public ResponseEntity<AuthenticationResponse> registerSuperAdmin(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerSuperAdmin(request));
    }
    @PostMapping("/registerResponsable")
    public ResponseEntity<AuthenticationResponse> registerResponsable(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerResponsable(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
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


    @PutMapping("/updateRole/{idUser}")

    public ResponseEntity<?> changeUserRole(@PathVariable Long idUser, @RequestBody Role newRole) {
        userService.changeUserRole(idUser, newRole);
        return ResponseEntity.ok().build();
    }
}