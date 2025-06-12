package org.callboard.services.authService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.authDto.AuthResponse;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.callboard.security.JwtProvider;
import org.callboard.services.userServices.UserRepositoryService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepositoryService userRepositoryService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticateUser(AuthenticationRequest request) throws AuthException {

        String username = request.getEmail();
        String password = request.getPassword();


        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("Request to create jwt token " + username);

            String jwt = jwtProvider.generateJwtToken(authentication.getName());

            return new AuthResponse(jwt);

        } catch (Exception e) {
            log.error("Authentication failed for user: " + username, e);
            throw new AuthException("Authentication failed for user: " + username, e);
        }

    }

}
