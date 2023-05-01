package Pi.Spring.Auth;


import Pi.Spring.Entity.User;
import Pi.Spring.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor

public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;


}
