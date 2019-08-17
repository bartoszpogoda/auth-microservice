package com.github.bartoszpogoda.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.bartoszpogoda.auth.entity.Role;
import com.github.bartoszpogoda.auth.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenProviderService {

    @Value("${auth.token.validity-seconds}")
    private int accessTokenValiditySeconds = 10 * 60;

    @Value("${auth.token.signing-key}")
    private String signingKey = "afaASuughBFN1212r";

    @Value("${auth.token.issuer}")
    private String issuer = "auth-service";

    @Value("${auth.token.claims.key.roles}")
    private String keyRoles = "roles";

    @Value("${auth.token.claims.key.id}")
    private String keyUserId;

    public String provide(User user) {
        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(calculateExpirationDate())
                .withSubject(user.getEmail())
                .withArrayClaim(keyRoles, extractUserRoleNames(user))
                .withClaim(keyUserId, user.getId())
                .sign(Algorithm.HMAC256(signingKey));
    }

    private Date calculateExpirationDate() {
        return Date.from(Instant.now().plusSeconds(accessTokenValiditySeconds));
    }

    private String[] extractUserRoleNames(User user) {
        return user.getRoles().stream().map(Role::getName).toArray(String[]::new);
    }

}
