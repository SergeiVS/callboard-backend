package org.callboard.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.callboard.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtProvider {
    @Value("HUHerfjewhewu47585903JOIiIJOU4QOoiu8h73")
    private  String jwtSecret;

    private final Key jwtAccessSecret = getSecretKey();

    @Value("300000")
    private  long expireAt;

   public String generateJwtSecret(User user) {
       final LocalDateTime now = LocalDateTime.now();
       final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
       final Date accessExpiration = Date.from(accessExpirationInstant);
       return Jwts.builder()
               .setSubject(user.getEmail())
               .setExpiration(accessExpiration)
               .signWith(jwtAccessSecret)
               .claim("roles", getRoles(user))
               .compact();

   }


    private List<String> getRoles(User user) {
       return user.getRoles().stream().map(role -> "Role_"+ role.getRoleName()).toList();
    }

    private boolean validateToken(@NotNull String token, @NotNull Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getAccessClaims(@NotNull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    private Claims getClaims(@NotNull String token, @NotNull Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    private SecretKey getSecretKey(){
        return new SecretKeySpec(Decoders.BASE64.decode(jwtSecret), "AES");
    };
}
