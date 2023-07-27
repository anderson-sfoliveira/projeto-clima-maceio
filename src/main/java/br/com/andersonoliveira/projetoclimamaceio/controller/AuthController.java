package br.com.andersonoliveira.projetoclimamaceio.controller;

import br.com.andersonoliveira.projetoclimamaceio.jwt.JwtTokenService;
import br.com.andersonoliveira.projetoclimamaceio.model.User;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.LoginResponseDTO;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.AuthenticationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = "/auth", produces = {"application/json"})
@Tag(name = "auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenService jwtTokenService;

	@Operation(summary = "Cria token de acesso.", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Token criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parametros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro ao processar os dados"),
	})
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDTO authenticationDTO) {
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
