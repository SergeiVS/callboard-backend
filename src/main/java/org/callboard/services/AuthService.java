package org.callboard.services;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    public ResponseEntity<StandardResponse> authenticateUser(AuthenticationRequest request) throws AuthException {

        String username = request.getEmail();
        String password = request.getPassword();

        return ResponseEntity.ok(new StandardResponse("Logged on"));
    }
}
