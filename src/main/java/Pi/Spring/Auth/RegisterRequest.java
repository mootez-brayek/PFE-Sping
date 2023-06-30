package Pi.Spring.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    public String name;
    @Column(unique = true)
    public String username;
    public String password;
    public String email;
    public String prenom;

}
