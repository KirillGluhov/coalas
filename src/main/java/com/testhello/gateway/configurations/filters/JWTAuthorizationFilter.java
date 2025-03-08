package com.testhello.gateway.configurations.filters;

import com.testhello.gateway.configurations.parsers.JWTParser;
import com.testhello.gateway.enums.Role;
import com.testhello.gateway.response.ResponseErrorSender;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends AbstractGatewayFilterFactory<JWTAuthorizationFilter.Config>
{
    @Autowired
    JWTParser jwtParser;
    ResponseErrorSender responseErrorSender = new ResponseErrorSender();

    public JWTAuthorizationFilter()
    {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            String bearerToken = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (bearerToken == null || !bearerToken.startsWith("Bearer "))
            {
                return responseErrorSender.sendErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "You haven't token");
            }

            String jwtToken = bearerToken.substring(7);
            Claims parsedToken = jwtParser.parseJWT(jwtToken);

            if (parsedToken == null)
            {
                return responseErrorSender.sendErrorResponse(exchange, HttpStatus.FORBIDDEN, "Token doesn't pass validation for expiration date or signature");
            }

            if (!hasRole(parsedToken, config))
            {
                return responseErrorSender.sendErrorResponse(exchange, HttpStatus.FORBIDDEN, "You haven't role");
            }

            return chain.filter(exchange);

        };
    }

    private boolean isTokenExpired(Date expirationDate)
    {
        if (expirationDate.after(new Date()))
        {
            return false;
        }
        return true;
    }

    private boolean hasRole(Claims parsedToken, Config config)
    {
        if (config.getRoles().size() == 0)
        {
            return true;
        }

        System.out.println(config.getRoles());
        System.out.println(parsedToken);

        for (Role role : config.getRoles())
        {
            if ((parsedToken.containsKey("role") || parsedToken.containsKey("Role")) && parsedToken.containsValue(role.toString()))
            {
                return true;
            }
        }

        return false;
    }

    public static class Config {
        private List<Role> roles = new ArrayList<>();

        public List<Role> getRoles() {
            return roles;
        }
        public Config addRole(Role role)
        {
            this.roles.add(role);
            return this;
        }
    }
}
