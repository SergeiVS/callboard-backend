package controller.api;

import org.springframework.security.core.Authentication;

public class JwtTokenProvider {
    private Authentication authentication;

    public String generateToken() {
        return generateToken(null);
    }

    public String generateToken(Authentication authentication) {
        this.authentication = authentication;

        return null;
    }

}
