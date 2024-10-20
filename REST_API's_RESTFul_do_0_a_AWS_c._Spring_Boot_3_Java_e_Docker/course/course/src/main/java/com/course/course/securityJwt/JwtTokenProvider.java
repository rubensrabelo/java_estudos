package com.course.course.securityJwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.course.course.data.vo.v1.security.TokenVO;
import com.course.course.exceptions.InvalidJwtAuthenticationException;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {
	
	@Value("${security.jwt.secret-key:secret}")
	private String secretKey = "secret";
	
	@Value("${security.jwt.expire-length:3600000}")
	private long validityInMilliseconds = 3600000;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	Algorithm algorithm = null;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}
	
	public TokenVO createAccessToken(String username, List<String> roles) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime validity = now.plusSeconds(validityInMilliseconds/1000);
		var accessToken = getAccessToken(username, roles, now, validity);
		var refreshToken = getRefreshToken(username, roles, now);
		
		return new TokenVO(username, true, now, validity, accessToken, refreshToken);
	}

	private String getRefreshToken(String username, List<String> roles, LocalDateTime now) {
		LocalDateTime validity = now.plusSeconds((validityInMilliseconds * 3)/1000);
		
		Date issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
	    Date expiration = Date.from(validity.atZone(ZoneId.systemDefault()).toInstant());

	    return JWT.create()
	            .withSubject(username)
	            .withClaim("roles", roles)
	            .withIssuedAt(issuedAt)  
	            .withExpiresAt(expiration) 
	            .sign(algorithm)
	            .strip();
	}

	private String getAccessToken(String username, List<String> roles, LocalDateTime now, LocalDateTime validity) {
		String issueUrl = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUriString();
		
		Date issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
	    Date expiration = Date.from(validity.atZone(ZoneId.systemDefault()).toInstant());

	    return JWT.create()
	            .withSubject(username)
	            .withClaim("roles", roles)
	            .withIssuedAt(issuedAt)  
	            .withExpiresAt(expiration) 
	            .withIssuer(issueUrl)  
	            .sign(algorithm)
	            .strip();
	}
	
	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJwt = decodedJwt(token);
		UserDetails userDetails = this.userDetailService.loadUserByUsername(decodedJwt.getSubject());
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private DecodedJWT decodedJwt(String token) {
		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		DecodedJWT decodedJwt = verifier.verify(token);
		
		return decodedJwt;
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		
		return null;
	}
	
	public boolean validateToken(String token) throws InvalidJwtAuthenticationException {
		DecodedJWT decodedJWT = decodedJwt(token);
		
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		
		try {
			if(decodedJWT.getExpiresAt().before(now)) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
	        throw new InvalidJwtAuthenticationException("Expired or invalid JWT token!");
		}
	}
}
