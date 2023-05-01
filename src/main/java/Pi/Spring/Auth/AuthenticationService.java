package Pi.Spring.Auth;


import Pi.Spring.Config.JwtService;
import Pi.Spring.Entity.Role;
import Pi.Spring.Entity.User;
import Pi.Spring.Repositury.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor


public class AuthenticationService {
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public AuthenticationResponse registerControlleur(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Controlleur)
                .build();
        userRepo.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse
                .builder()
                .aceesToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepo.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .aceesToken(jwtToken)
                .build();
    }



    public AuthenticationResponse registerSuperAdmin(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.SuperAdmin)
                .build();
        userRepo.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .aceesToken(jwtToken)
                .build();
    }
    public AuthenticationResponse registerResponsable(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Responsable_General)
                .build();
        userRepo.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .aceesToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse
                .builder()
                .aceesToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader= request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if(authHeader == null ||!authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null){
            var userDetails = this.userRepo.findByUsername(username).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, userDetails)){
                var accessToken = jwtService.generateToken(userDetails);
                var authResponse = AuthenticationResponse.builder()
                        .aceesToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
