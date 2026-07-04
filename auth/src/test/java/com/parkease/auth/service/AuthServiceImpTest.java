package com.parkease.auth.service;

import com.parkease.auth.dto.AuthResponse;
import com.parkease.auth.dto.UserRegistration;
import com.parkease.auth.dto.UserRequest;
import com.parkease.auth.dto.UserResponse;
import com.parkease.auth.entity.User;
import com.parkease.auth.enums.Role;
import com.parkease.auth.repository.UserRepository;
import com.parkease.auth.security.JwtUtil;
import com.parkease.auth.util.ResponseStructure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomerUserDetails customerUserDetails;

    @Mock
    private TokenBlacklistService tokenBlacklistService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AuthServiceImp authService;

    @Test
    void registerShouldCreateUserAndReturnCreatedResponse() {
        UserRegistration request = new UserRegistration();
        request.setUsername("sudha");
        request.setEmail("sudha@example.com");
        request.setPassword("password123");
        request.setPhone("9876543210");
        request.setRole(Role.DRIVER);

        User savedUser = User.builder()
                .userId(1)
                .username("sudha")
                .email("sudha@example.com")
                .password("encoded-password")
                .phone("9876543210")
                .role(Role.DRIVER)
                .isActive(true)
                .build();

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername("sudha");
        userResponse.setEmail("sudha@example.com");
        userResponse.setPhone("9876543210");
        userResponse.setRole(Role.DRIVER);

        when(passwordEncoder.encode("password123")).thenReturn("encoded-password");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(modelMapper.map(savedUser, UserResponse.class)).thenReturn(userResponse);

        ResponseEntity<ResponseStructure<UserResponse>> response = authService.register(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getBody().getMsg()).isEqualTo("User Registrered Successfully");
        assertThat(response.getBody().getData()).isEqualTo(userResponse);

        verify(userRepository).save(any(User.class));
        verify(passwordEncoder).encode("password123");
    }

    @Test
    void registerShouldThrowExceptionWhenRepositorySaveFails() {
        UserRegistration request = new UserRegistration();
        request.setUsername("sudha");
        request.setEmail("sudha@example.com");
        request.setPassword("password123");
        request.setPhone("9876543210");
        request.setRole(Role.DRIVER);

        when(passwordEncoder.encode("password123")).thenReturn("encoded-password");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Database error"));

        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Database error");

        verify(modelMapper, never()).map(any(User.class), any());
    }

    @Test
    void loginShouldAuthenticateAndReturnAccessAndRefreshTokens() {
        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);
        UserDetails userDetails = org.mockito.Mockito.mock(UserDetails.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("sudha");
        when(jwtUtil.generateToken("sudha")).thenReturn("access-token");
        when(jwtUtil.generateRefreshToken("sudha")).thenReturn("refresh-token");

        AuthResponse response = authService.login(new UserRequest());

        assertThat(response.getAccessToken()).isEqualTo("access-token");
        assertThat(response.getRefreshToken()).isEqualTo("refresh-token");
    }

    @Test
    void loginShouldThrowExceptionWhenCredentialsAreInvalid() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThatThrownBy(() -> authService.login(new UserRequest()))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Bad credentials");

        verify(jwtUtil, never()).generateToken(any());
        verify(jwtUtil, never()).generateRefreshToken(any());
    }

    @Test
    void logoutShouldBlacklistToken() {
        authService.logout("token");

        verify(tokenBlacklistService).blacklist("token");
    }

    @Test
    void validateTokenShouldReturnTrueWhenTokenIsValidAndNotBlacklisted() {
        when(jwtUtil.extractUsername("token")).thenReturn("sudha");
        when(jwtUtil.validateToken("token", "sudha")).thenReturn(true);
        when(tokenBlacklistService.isBlacklisted("token")).thenReturn(false);

        boolean result = authService.validateToken("token");

        assertThat(result).isTrue();
    }

    @Test
    void validateTokenShouldReturnFalseWhenTokenIsBlacklisted() {
        when(jwtUtil.extractUsername("token")).thenReturn("sudha");
        when(jwtUtil.validateToken("token", "sudha")).thenReturn(true);
        when(tokenBlacklistService.isBlacklisted("token")).thenReturn(true);

        boolean result = authService.validateToken("token");

        assertThat(result).isFalse();
    }

    @Test
    void validateTokenShouldReturnFalseWhenUsernameIsMissing() {
        when(jwtUtil.extractUsername("token")).thenReturn(null);

        boolean result = authService.validateToken("token");

        assertThat(result).isFalse();
        verify(jwtUtil, never()).validateToken(any(), any());
        verify(tokenBlacklistService, never()).isBlacklisted(any());
    }

    @Test
    void validateTokenShouldReturnFalseWhenJwtValidationFails() {
        when(jwtUtil.extractUsername("token")).thenReturn("sudha");
        when(jwtUtil.validateToken("token", "sudha")).thenReturn(false);

        boolean result = authService.validateToken("token");

        assertThat(result).isFalse();
        verify(tokenBlacklistService, never()).isBlacklisted(any());
    }

    @Test
    void refreshTokenShouldReturnNewAccessTokenWhenRefreshTokenIsValid() {
        when(jwtUtil.extractUsername("refresh-token")).thenReturn("sudha");
        when(jwtUtil.validateToken("refresh-token", "sudha")).thenReturn(true);
        when(jwtUtil.generateToken("sudha")).thenReturn("new-access-token");

        String token = authService.refreshToken("refresh-token");

        assertThat(token).isEqualTo("new-access-token");
    }

    @Test
    void refreshTokenShouldThrowExceptionWhenRefreshTokenIsInvalid() {
        when(jwtUtil.extractUsername("refresh-token")).thenReturn("sudha");
        when(jwtUtil.validateToken("refresh-token", "sudha")).thenReturn(false);

        assertThatThrownBy(() -> authService.refreshToken("refresh-token"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Invalid refresh token");
    }

    @Test
    void refreshTokenShouldThrowExceptionWhenUsernameIsMissing() {
        when(jwtUtil.extractUsername("refresh-token")).thenReturn(null);

        assertThatThrownBy(() -> authService.refreshToken("refresh-token"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Invalid refresh token");

        verify(jwtUtil, never()).generateToken(any());
    }

    @Test
    void getUserByEmailShouldReturnUser() {
        User user = User.builder().email("sudha@example.com").build();
        when(userRepository.findByEmail("sudha@example.com")).thenReturn(Optional.of(user));

        User result = authService.getUserByEmail("sudha@example.com");

        assertThat(result).isEqualTo(user);
    }

    @Test
    void getUserByEmailShouldThrowExceptionWhenUserDoesNotExist() {
        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.getUserByEmail("missing@example.com"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void getUserByIdShouldReturnUser() {
        User user = User.builder().userId(1).build();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = authService.getUserById(1);

        assertThat(result).isEqualTo(user);
    }

    @Test
    void getUserByIdShouldThrowExceptionWhenUserDoesNotExist() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.getUserById(99))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void updateProfileShouldUpdateEditableFields() {
        User existingUser = User.builder()
                .userId(1)
                .username("old")
                .phone("111")
                .profilePicUrl("old.png")
                .build();
        User update = User.builder()
                .username("new")
                .phone("222")
                .profilePicUrl("new.png")
                .build();

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = authService.updateProfile(1, update);

        assertThat(result.getUsername()).isEqualTo("new");
        assertThat(result.getPhone()).isEqualTo("222");
        assertThat(result.getProfilePicUrl()).isEqualTo("new.png");
        verify(userRepository).save(existingUser);
    }

    @Test
    void updateProfileShouldThrowExceptionWhenUserDoesNotExist() {
        User update = User.builder()
                .username("new")
                .phone("222")
                .profilePicUrl("new.png")
                .build();

        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.updateProfile(99, update))
                .isInstanceOf(NoSuchElementException.class);

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void changePasswordShouldEncodeAndSavePassword() {
        User user = User.builder().userId(1).password("old-password").build();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("new-password")).thenReturn("encoded-new-password");

        authService.changePassword(1, "new-password");

        assertThat(user.getPassword()).isEqualTo("encoded-new-password");
        verify(userRepository).save(user);
    }

    @Test
    void changePasswordShouldThrowExceptionWhenUserDoesNotExist() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.changePassword(99, "new-password"))
                .isInstanceOf(NoSuchElementException.class);

        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deactivateAccountShouldSetIsActiveFalseAndSave() {
        User user = User.builder().userId(1).isActive(true).build();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        authService.deactivateAccount(1);

        assertThat(user.getIsActive()).isFalse();
        verify(userRepository).save(user);
    }

    @Test
    void deactivateAccountShouldThrowExceptionWhenUserDoesNotExist() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.deactivateAccount(99))
                .isInstanceOf(NoSuchElementException.class);

        verify(userRepository, never()).save(any(User.class));
    }
}
