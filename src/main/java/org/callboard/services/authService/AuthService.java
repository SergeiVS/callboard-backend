package org.callboard.services.authService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.authDto.AuthResponse;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    public AuthResponse authenticateUser(AuthenticationRequest request) throws AuthException {

        String username = request.getEmail();
        String password = request.getPassword();

        log.info(STR."UserName: \{username} Password: \{password}");

        return new AuthResponse();
    }
}
