package org.callboard.services.securityService;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateJwtService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public String createJwt(String username, String password) throws AuthException {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtProvider.getJwtToken(username);
        }catch (Exception e) {
           throw new AuthException(e.getMessage());
        }
    }
}
