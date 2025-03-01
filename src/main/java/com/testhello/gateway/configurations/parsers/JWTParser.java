package com.testhello.gateway.configurations.parsers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTParser {

    @Value("${jwt.secret}")
    private String secret;

    public Claims parseJWT(String jwtToken)
    {
        try
        {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            Claims payload = Jwts.parser().setSigningKey(key).build().parseSignedClaims(jwtToken).getPayload();
            return payload;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

