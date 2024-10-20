package com.course.course.securityJwt;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;
import com.course.course.data.vo.v1.security.TokenVO;
import com.github.dozermapper.core.converters.LocalDateTimeConverter;

import jakarta.annotation.PostConstruct;

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
		return null;
	}

	private String getAccessToken(String username, List<String> roles, LocalDateTime now, LocalDateTime validity) {
		return null;
	}
}
