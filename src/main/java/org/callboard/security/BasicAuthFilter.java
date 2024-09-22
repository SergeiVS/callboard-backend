package org.callboard.security;

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
import org.springframework.security.web.AuthenticationEntryPoint;
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

    private Authentication extractAuthentication(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        byte[] credDecoded = Base64.getDecoder().decode(authHeader);
        String credentials = new String(credDecoded);

        final String[] credValues = credentials.split(":", 2);
        if (credValues.length != 2) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String email = credValues[0];
        String password = credValues[1];

        return new UsernamePasswordAuthenticationToken(email, password, null);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            try {
                Authentication authentication = getAuthenticationManager().authenticate(extractAuthentication(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }
    }
}
