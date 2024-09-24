package org.callboard.services.securityService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.exceptions.InvalidJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {
//TODO remove open jwtSecret before project deploying
    @Value("HUHerfjewhewu47585903JOIiIJOU4QOoiu8h73")
    private String jwtSecret;

    @Value("300000")
    private long expireAt;



    public String getJwtToken(String login) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expireAt);
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        getClaimsFromToken(token);
        return true;
    }

    public String getLoginFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new InvalidJwtException("Invalid JWT token");
        }
    }

    private Key getKey() {
        return new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
    }

}
