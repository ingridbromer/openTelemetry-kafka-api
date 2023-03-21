package br.com.example.openTelemetryKafka.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.example.openTelemetryKafka.api.restClient.RestAuthorization;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private RestAuthorization restAuthorization;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			RestAuthorization restAuthorization) {
		super(authenticationManager);
		this.restAuthorization = restAuthorization;
	}

	// Filtro de request HTTP para validação do token JWT
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = restAuthorization.getAuthorization(header.substring(7));
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}
}