package med.voll.api.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.infra.security.JwtService;
import med.voll.api.web.dto.JwtDto;
import med.voll.api.web.dto.LoginDto;

@RestController
@RequestMapping("login")
public interface AuthController {
	
	private final AuthenticationManager manager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager manager, JwtService jwtService) {
        this.manager = manager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<JwtDto> login(@RequestBody @Valid LoginDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);

        var jwt = jwtService.gerarJwt((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtDto(jwt));
    }
}
