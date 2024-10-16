package org.callboard.services.authService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.authDto.AuthResponse;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.callboard.security.JwtProvider;
import org.callboard.services.userServices.UserRepositoryService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepositoryService userRepositoryService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticateUser(AuthenticationRequest request) throws AuthException {

        String username = request.getEmail();
        String password = request.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info(STR."Запрос на создание jwt от \{username}, \{password}");

            String jwt = jwtProvider.generateJwtToken(authentication.getName());

            return new AuthResponse(jwt);
        } catch (Exception e) {
            log.error(STR."Authentication failed for user: \{username}", e);
            throw e;
        }
    }

}
