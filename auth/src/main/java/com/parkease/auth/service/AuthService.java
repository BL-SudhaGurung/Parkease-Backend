package com.parkease.auth.service;

import com.parkease.auth.dto.AuthResponse;
import com.parkease.auth.dto.UserRegistration;
import com.parkease.auth.dto.UserRequest;
import com.parkease.auth.dto.UserResponse;
import com.parkease.auth.entity.User;
import com.parkease.auth.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<ResponseStructure<UserResponse>> register(UserRegistration user);

    AuthResponse login(UserRequest user);

    void logout(String token);

    boolean validateToken(String token);

    String refreshToken(String token);

    User getUserByEmail(String email);

    UserResponse getUserResponseByUsername(String username);

    User getUserById(Integer userId);

    User updateProfile(Integer userId,User user);

    void changePassword(Integer userId,String password);

    void deactivateAccount(Integer userId);
}
