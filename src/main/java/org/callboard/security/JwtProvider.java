package org.callboard.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.callboard.entities.User;
import org.eclipse.angus.mail.util.BASE64DecoderStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("HUHerfjewhewu47585903JOIiIJOU4QOoiu8h73")
    private  String jwtSecret;

    private final Key jwtAccessSecret = getSecretKey();

    @Value("300000")
    private  long expireAt;

   public String getJwtSecret(User user) {
       final LocalDateTime now = LocalDateTime.now();
       final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
       final Date accessExpiration = Date.from(accessExpirationInstant);
       return Jwts.builder()
               .setSubject(user.getEmail())
               .setExpiration(accessExpiration)
               .signWith(jwtAccessSecret)
               .claim("roles", user.getRoles().stream().map(role -> STR."ROLE_\{role}"))
               .compact();

   }
    public boolean validateToken(@NotBlank String token) {
        return validateToken(token, jwtAccessSecret);
    }


    private boolean validateToken(@NotNull String token, @NonNull Key secret) {
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
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
    }


    private SecretKey getSecretKey(){
        return new SecretKeySpec(Decoders.BASE64.decode(jwtSecret), "AES");
    };
}
