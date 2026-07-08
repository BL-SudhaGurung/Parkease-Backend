package com.parkease.auth.security;

import com.parkease.auth.entity.User;
import com.parkease.auth.enums.Role;
import com.parkease.auth.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setUsername(name);
            newUser.setRole(Role.DRIVER);
            newUser.setIsActive(true);
            return userRepository.save(newUser);
        });

        String accessToken = jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRole()));
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        log.info("OAuth2 login successful for email: {}", email);
        response.sendRedirect("/api/v1/auth/oauth2/success?accessToken=" + accessToken + "&refreshToken=" + refreshToken);
    }
}
