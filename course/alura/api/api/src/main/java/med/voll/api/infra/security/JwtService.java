package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.domain.user.User;

@Service
public class JwtService {
	
	private final String issuer;
	private final String secret;
	
	public JwtService(@Value("${app.security.token.issuer}") String issuer, @Value("${app.security.token.secret}") String secret) {
		this.issuer = issuer;
		this.secret = secret;
	}
	
	public String getJWT(User user) {
		try {
			return JWT.create()
					.withIssuer(issuer)
					.withSubject(user.getId().toString())
					.withExpiresAt(dateExpiration())
					.sign(algorithm());
		} catch (JWTCreationException  e) {
			throw new RuntimeException("Erro ao gerar JWT", e);
		}
	}
	

	public UserData userData(String jwt) {
		 try {
	            var verifier = JWT.require(algorithm())
	                    .withIssuer(issuer)
	                    .build()
	                    .verify(jwt);
	            return new UserData(Long.parseLong(verifier.getSubject()), verifier.getClaim("role").asString());
	        } catch (JWTVerificationException exception) {
	            throw new RuntimeException("JWT inválido ou expirado:" +jwt);
	        }
	}
	
	private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }
	
	private Instant dateExpiration() {
		return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
	}
}
