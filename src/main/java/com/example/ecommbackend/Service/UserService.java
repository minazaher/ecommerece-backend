package com.example.ecommbackend.Service;


import com.example.ecommbackend.DTO.AuthenticationRequest;
import com.example.ecommbackend.DTO.AuthenticationResponse;
import com.example.ecommbackend.DTO.RegisterRequest;
import com.example.ecommbackend.Model.Role;
import com.example.ecommbackend.Model.User;
import com.example.ecommbackend.Repository.UserRepository;
import com.example.ecommbackend.Repository.WishlistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        User user = findUserIfExists(request.getEmail());
        if (user != null){
            new AuthenticationResponse();
            return AuthenticationResponse.builder().token("user already exist").build();
        }
        user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String jwtToken = "User not found";
        User user = findUserIfExists(request.getEmail());

        if (user != null && isPasswordValid(user, request)){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()
            ));
            jwtToken = jwtService.generateToken(user);
        }

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public User findUserIfExists(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean isPasswordValid(User user, AuthenticationRequest request) {
        boolean result = passwordEncoder.matches(request.getPassword(), user.getPassword());
        return result;
    }


}