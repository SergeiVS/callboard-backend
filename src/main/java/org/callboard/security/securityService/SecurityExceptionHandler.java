package org.callboard.security.securityService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

public class SecurityExceptionHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    public static final AuthenticationEntryPoint ENTRY_POINT = (request, response, authException) -> {
        String message = authException.getMessage();

        fillResponse(response, HttpStatus.UNAUTHORIZED, message);
    };

    public static final AccessDeniedHandler ACCESS_DENY_HANDLER = (request, response, authException) -> {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (authentication != null && authentication.getName() != null) ? authentication.getName(): "anonymous";
        String message = "Access denied for user: " + userName;
        fillResponse(response, HttpStatus.FORBIDDEN, message);
    };

    private static void fillResponse(HttpServletResponse response, HttpStatus status, String message) {
        try {
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            String standardResponse = message + ": " + status.getReasonPhrase();
            response.getWriter().write(standardResponse);

        } catch (Exception e) {
            throw new IllegalStateException("Could not fill response", e);
        }
    }
}
