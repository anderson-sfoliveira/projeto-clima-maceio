package br.com.andersonoliveira.projetoclimamaceio.jwt;

import java.io.IOException;

import br.com.andersonoliveira.projetoclimamaceio.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = jwtTokenService.resolveToken((HttpServletRequest) request);

		if(token != null){
			var login = jwtTokenService.validateToken(token);

			if (login != "") {
				Authentication auth = jwtTokenService.getAuthentication(login);

				if (auth != null) {
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}

		filterChain.doFilter(request, response);
	}
}
