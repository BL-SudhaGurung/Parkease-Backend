package com.parkease.auth.resource;

import com.parkease.auth.dto.UserRegistration;
import com.parkease.auth.dto.UserRequest;
import com.parkease.auth.dto.UserResponse;
import com.parkease.auth.service.AuthService;
import com.parkease.auth.util.ResponseStructure;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthResource {


    private final AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(authService.getUserResponseByUsername(username));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserRequest request) {
        log.info("Login request received for user: {}", request.getUsername());
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> register(@Valid @RequestBody UserRegistration user) {
        log.info("Register request received for email: {}", user.getEmail());
        return authService.register(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        authService.logout(authHeader.substring(7));
        log.info("Logout successful");
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
        log.info("Token refresh request received");
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @GetMapping("/oauth2/success")
    public ResponseEntity<?> oauth2Success(@RequestParam String accessToken,
                                           @RequestParam String refreshToken) {
        return ResponseEntity.ok(new com.parkease.auth.dto.AuthResponse(accessToken, refreshToken));
    }
}
