package br.com.superatecnologia.managementapi.utils;

import br.com.superatecnologia.managementapi.dtos.responses.JwtResponseDTO;
import br.com.superatecnologia.managementapi.exceptions.TokenException;
import br.com.superatecnologia.managementapi.exceptions.enums.TokenEnum;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JwtUtil {

    @Value("${auth.util.jwt.secret}")
    private String SECRET;

    private final String ISSUER = "supera-tecnologia";

    private static final String KEY_EMAIL = "EMAIL";
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";

    public String generateTokenJwt(Long id, String email, String name) {
        try {
            log.info("generating jwt token for user {}", email);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim(KEY_ID, id)
                    .withClaim(KEY_EMAIL, email)
                    .withClaim(KEY_NAME, name)
                    .withExpiresAt(new Date(Instant.now().toEpochMilli() + TimeUnit.HOURS.toMillis(1)))//1h
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (JWTCreationException exception) {
            log.warn("error when trying to create the token");
            throw new TokenException(TokenEnum.INVALID_TOKEN);
        }
    }

    public void validateToken(String jwtToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            verifier.verify(jwtToken);
        } catch (Exception e) {
            log.warn("invalid token");
            throw new TokenException(TokenEnum.INVALID_TOKEN);
        }
    }

    public JwtResponseDTO getAllClaims(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();

        DecodedJWT jwt = verifier.verify(token);

        return new JwtResponseDTO(jwt.getClaim("ID").asLong(), jwt.getClaim("EMAIL").asString(), jwt.getClaim("NAME").asString());
    }
}