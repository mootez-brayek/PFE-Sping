package Pi.Spring.Controller;


import Pi.Spring.requests.ForgotPasswordRequest;
import Pi.Spring.requests.ResetPasswordRequest;
import Pi.Spring.Entity.User;
import Pi.Spring.Service.EmailService;
import Pi.Spring.requests.MessageRequests;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/Reset-Password/")
@RequiredArgsConstructor
@Transactional
@CrossOrigin("*")
@Slf4j
public class ResetPasswordController {

    @Autowired
    EmailService emailService;


    @PostMapping("/forget")
    public ResponseEntity<MessageRequests> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        emailService.processForgotPassword(request);
        return ResponseEntity.ok(new MessageRequests("Password reset initiated successfully. Please check your email"));
    }

    @PostMapping("/reset")
    public ResponseEntity<MessageRequests> resetPassword(@RequestBody ResetPasswordRequest request) {
        Boolean b = emailService.resetPassword(request);
        if(b){
            return ResponseEntity.ok().body(new MessageRequests("password changed"));
        }else
        return ResponseEntity.badRequest().body(new MessageRequests("wrong token"));
    }
}
