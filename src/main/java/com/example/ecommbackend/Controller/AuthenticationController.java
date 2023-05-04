package com.example.ecommbackend.Controller;


import com.example.ecommbackend.DTO.AuthenticationRequest;
import com.example.ecommbackend.DTO.AuthenticationResponse;
import com.example.ecommbackend.DTO.RegisterRequest;
import com.example.ecommbackend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }
    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok((userService.authenticate(request)));
    }
}

