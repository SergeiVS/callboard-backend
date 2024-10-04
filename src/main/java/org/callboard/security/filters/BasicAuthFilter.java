package org.callboard.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
@Slf4j
public class BasicAuthFilter extends BasicAuthenticationFilter {

    public BasicAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            try {
                Authentication authentication = getAuthenticationManager().authenticate(extractAuthentication(authHeader));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (BadCredentialsException e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request");
            }
        }
        chain.doFilter(request, response);
    }

    private Authentication extractAuthentication(String header) {

        String base64Credentials= header.substring("Basic ".length());
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded);

        final String[] credValues = credentials.split(":", 2);

        if (credValues.length != 2) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String email = credValues[0];
        String password = credValues[1];

        log.info(STR."Email: \{email}");
        log.info(STR."Password: \{password}");

        return new UsernamePasswordAuthenticationToken(email, password, null);
    }
}
