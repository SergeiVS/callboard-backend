package org.callboard.controllers;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.callboard.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody AuthenticationRequest request) throws AuthException {
        log.info("Authenticating user: {}", request);
        return authService.authenticateUser(request);
    }
}
