package Pi.Spring.Service;

import Pi.Spring.Entity.TokenPassword;
import Pi.Spring.Repositury.TokenRepo;
import Pi.Spring.requests.ForgotPasswordRequest;
import Pi.Spring.requests.ResetPasswordRequest;
import Pi.Spring.Repositury.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;

    @Autowired
    UserRepo userRepo;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public TokenRepo tokenRepo;


    @Override
    public void processForgotPassword(ForgotPasswordRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            TokenPassword token = new TokenPassword();
            token.setToken(UUID.randomUUID().toString());
            token.setUser(userRepo.findByEmail(request.getEmail()));
            token.setExpirationDate(LocalDateTime.now().plusMinutes(30));
            tokenRepo.save(token);
            sendResetEmail(request.getEmail(), token.getToken());
        }
    }

    public void sendResetEmail(String email, String resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset");
        message.setText("Click the link below to reset your password:\n"
                + "http://localhost:4200/reset-Password?token=" + resetToken +"&email="+ email);

        javaMailSender.send(message);
    }

    public Boolean resetPassword(ResetPasswordRequest request) {

        Optional<TokenPassword> tokenPasswordOptional= tokenRepo.findByToken(request.getResetToken());
        if(tokenPasswordOptional.isPresent()){
            TokenPassword tokenPassword = tokenPasswordOptional.get();
            if(tokenPassword.getUser().getEmail().equals(request.getEmail())){
                if(!tokenPassword.getExpirationDate().isBefore(LocalDateTime.now())){
                    tokenPassword.getUser().setPassword(passwordEncoder.encode(request.getNewPassword()));
                    tokenPassword.setExpirationDate(LocalDateTime.now().minusMinutes(1));
                    tokenRepo.save(tokenPassword);
                    return true;
                }
            }
        }
        return false;
    }
}
