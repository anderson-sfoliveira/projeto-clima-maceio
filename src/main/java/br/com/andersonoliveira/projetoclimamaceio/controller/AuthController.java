package br.com.andersonoliveira.projetoclimamaceio.controller;

import br.com.andersonoliveira.projetoclimamaceio.jwt.JwtTokenService;
import br.com.andersonoliveira.projetoclimamaceio.model.User;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.LoginResponseDTO;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.AuthenticationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenService jwtTokenService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
		try {
			var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.userName(), authenticationDTO.password());
			var auth = this.authenticationManager.authenticate(usernamePassword);
			var token = jwtTokenService.createToken((User) auth.getPrincipal());

			return ResponseEntity.ok(new LoginResponseDTO(token));
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Nome e/ou senha incorretos.");
		}
	}
}
