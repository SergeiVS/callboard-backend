package org.callboard.services;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.callboard.services.securityService.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> authenticateUser(AuthenticationRequest request) throws AuthException {

        String username = request.getEmail();
        String password = request.getPassword();

        log.info("Authenticating user {}", username);

        try {


            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtProvider.getJwtToken(username);

            log.info("Jwt token: {}", jwtToken);

            return ResponseEntity.ok(jwtToken);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }
}
