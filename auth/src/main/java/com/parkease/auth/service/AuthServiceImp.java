package com.parkease.auth.service;

import com.parkease.auth.dto.AuthResponse;
import com.parkease.auth.dto.UserRegistration;
import com.parkease.auth.dto.UserRequest;
import com.parkease.auth.dto.UserResponse;
import com.parkease.auth.entity.User;
import com.parkease.auth.enums.ErrorCode;
import com.parkease.auth.exception.ApiException;
import com.parkease.auth.repository.UserRepository;
import com.parkease.auth.security.JwtUtil;
import com.parkease.auth.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomerUserDetails customerUserDetails;
    private final TokenBlacklistService tokenBlacklistService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public ResponseEntity<ResponseStructure<UserResponse>> register(UserRegistration request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new ApiException(ErrorCode.EMAIL_ALREADY_EXISTS);
        if (userRepository.existsByUsername(request.getUsername()))
            throw new ApiException(ErrorCode.USERNAME_ALREADY_EXISTS);

        ResponseStructure<UserResponse> rs = new ResponseStructure<>();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setIsActive(true);
        User savedUser = userRepository.save(user);
        UserResponse user1 = modelMapper.map(savedUser, UserResponse.class);
        rs.setMsg("User Registered Successfully");
        rs.setData(user1);
        rs.setStatus(HttpStatus.CREATED.value());
        log.info("User Registered with Id: {}", savedUser.getUserId());
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    @Override
    public AuthResponse login(UserRequest user) {
        log.info("Login attempt for user: {}", user.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("Login successful for user: {}", user.getUsername());
        return new AuthResponse(jwtUtil.generateToken(userDetails.getUsername()), jwtUtil.generateRefreshToken(userDetails.getUsername()));
    }

    @Override
    public void logout(String token) {
        tokenBlacklistService.blacklist(token);
        log.info("Token blacklisted on logout");
    }

    @Override
    public boolean validateToken(String token) {
        String username = jwtUtil.extractUsername(token);
        return username != null && jwtUtil.validateToken(token, username) && !tokenBlacklistService.isBlacklisted(token);
    }




    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow();
    }

    @Override
    public String refreshToken(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);
        if (username == null || !jwtUtil.validateToken(refreshToken, username))
            throw new RuntimeException("Invalid refresh token");
//        return new AuthResponse(
//                jwtUtil.generateToken(username),
//                jwtUtil.generateRefreshToken(username)
//        );
        return jwtUtil.generateToken(username);
    }
    @Override
    public User getUserById(Integer userId) {

        return userRepository.findById(userId)
                .orElseThrow();
    }

    @Override
    public User updateProfile(Integer userId, User user) {

        User existingUser = getUserById(userId);

        existingUser.setUsername(user.getUsername());
        existingUser.setPhone(user.getPhone());
        existingUser.setProfilePicUrl(user.getProfilePicUrl());

        return userRepository.save(existingUser);
    }

    @Override
    public void changePassword(Integer userId,
                               String password) {

        User user = getUserById(userId);

        user.setPassword(
                passwordEncoder.encode(password));

        userRepository.save(user);
    }

    @Override
    public void deactivateAccount(Integer userId) {

        User user = getUserById(userId);

        user.setIsActive(false);

        userRepository.save(user);
    }
}
