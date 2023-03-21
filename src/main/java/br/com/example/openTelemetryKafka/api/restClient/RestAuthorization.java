package br.com.example.openTelemetryKafka.api.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.example.openTelemetryKafka.api.mapper.UserNamePasswordMapper;
import br.com.example.openTelemetryKafka.api.model.dto.UserNamePasswordDTO;

@Component
public class RestAuthorization {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UserNamePasswordMapper mapper;

	@Value("${service.authorization.baseUrl}")
	private String baseUrl;

	public UsernamePasswordAuthenticationToken getAuthorization(String username) {
		return mapper.toEntity(restTemplate.getForObject(baseUrl + "authorization/" + username, UserNamePasswordDTO.class));
	}
}
