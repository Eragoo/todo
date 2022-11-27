package com.Eragoo.todo.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Component
public class TokenProvider {

    private SecurityProps securityProps;

    public String createToken(Long id, String username) {
        return JWT.create()
                .withClaim("id", id)
                .withClaim("email", username)
                .withExpiresAt(Date.from(Instant.now().plus(securityProps.getLifetime())))
                .sign(Algorithm.HMAC512(securityProps.getSignature().getBytes()));
    }

    public Optional<AuthenticatedUser> parseUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(securityProps.getSignature().getBytes())
                .parseClaimsJws(token)
                .getBody();

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(
                claims.get("id", Long.class),
                claims.get("email", String.class)
        );
        return Optional.of(authenticatedUser);
    }
}
