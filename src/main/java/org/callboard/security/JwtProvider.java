package org.callboard.security;

import io.jsonwebtoken.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.entities.User;
import org.callboard.exceptions.InvalidJwtException;
import org.callboard.services.userServices.UserRepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    private final UserRepositoryService userRepositoryService;

    private final Key jwtAccessSecret = getSecretKey();

    @Value("300000")
    private long expireAt;

    public String generateJwtToken(String email) {
        User user = userRepositoryService.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        final Date now = new Date();
        final Date accessExpiration = new Date(now.getTime() + expireAt);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(accessExpiration)
                .claim("roles", getRoles(user))
                .signWith(jwtAccessSecret)
                .compact();

    }


    public boolean validateToken(@NotNull String token) {
        getClaims(token);
        return true;
    }

    public String getUserName(@NotNull String token) {
        return getClaims(token).getSubject();
    }


    private Claims getClaims(@NotNull String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new InvalidJwtException("Invalid JWT token: " + e.getMessage());
        }

    }


    private Key getSecretKey() {
        String jwtSecret = "HUHerfjewhewu47585903JOIiIJOU4QOoiu8h73";
        return new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    private List<String> getRoles(User user) {
        return user.getRoles().stream().map(role -> "Role_" + role.getRoleName()).toList();
    }
}
