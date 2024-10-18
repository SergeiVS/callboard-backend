package org.callboard.controllers.authController;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.authDto.AuthResponse;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.callboard.dto.userDto.NewUserRequest;
import org.callboard.dto.userDto.UserResponse;
import org.callboard.services.authService.AuthService;
import org.callboard.services.userServices.CreateUserService;
import org.callboard.services.userServices.FindUserByEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final CreateUserService createUserService;
    private final FindUserByEmailService findUserByEmailService;

    @PostMapping
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthenticationRequest request) throws AuthException {
        log.info("Authenticating user: {}", request);
        return ResponseEntity.ok(authService.authenticateUser(request));
    }

    @PostMapping("/signing")
    public ResponseEntity<StandardResponse> registerNewUser(@Valid @RequestBody NewUserRequest request) throws AuthException {
        return ResponseEntity.ok(createUserService.execute(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUserData(@NotNull(message = "User is not authorized") Principal principal) throws AuthException {

        UserResponse response = findUserByEmailService.execute(new StandardStringRequest(principal.getName()));

        return ResponseEntity.ok(response);
    }
}
