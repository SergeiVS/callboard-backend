package org.callboard.controllers;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.callboard.dto.userDto.NewUserRequest;
import org.callboard.dto.userDto.UserResponse;
import org.callboard.services.AuthService;
import org.callboard.services.userServices.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final CreateUserService createUserService;

    @PostMapping
    public ResponseEntity<StandardResponse> authenticateUser(@Valid @RequestBody AuthenticationRequest request) throws AuthException {
        log.info("Authenticating user: {}", request);
        return ResponseEntity.ok(authService.authenticateUser(request));
    }

    @PostMapping("/signing")
    public ResponseEntity<UserResponse> signing(@Valid @RequestBody NewUserRequest request) throws AuthException {
        return ResponseEntity.ok(createUserService.execute(request));
    }
}
