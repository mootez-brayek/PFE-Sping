package Pi.Spring.Service;

import Pi.Spring.requests.ForgotPasswordRequest;
import Pi.Spring.requests.ResetPasswordRequest;
import Pi.Spring.Entity.User;

public interface EmailService {
    public void processForgotPassword(ForgotPasswordRequest email);

    public void sendResetEmail(String email, String resetToken);

    public Boolean resetPassword(ResetPasswordRequest request);
}
