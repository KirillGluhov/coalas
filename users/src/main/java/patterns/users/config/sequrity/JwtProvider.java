package patterns.users.config.sequrity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    private static final SecretKey jwtAccessSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final SecretKey jwtRefreshSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateAccessToken(String userId, String role) {
        final Instant accessExpirationInstant = LocalDateTime.now()
                .plusMinutes(5)
                .atZone(ZoneId.systemDefault())
                .toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setExpiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .compact();
    }

    public static String generateRefreshToken(String userId) {
        final Instant refreshExpirationInstant = LocalDateTime.now()
                .plusDays(30)
                .atZone(ZoneId.systemDefault())
                .toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public static String extractUserId(@NonNull String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtAccessSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    public static Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private static Claims getClaims(@NonNull String token, @NonNull Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

