package br.com.example.openTelemetryKafka.api.mapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.example.openTelemetryKafka.api.model.dto.UserNamePasswordDTO;

@Service
public class UserNamePasswordMapper {

	// Mapper boiler plate
	public UsernamePasswordAuthenticationToken toEntity(UserNamePasswordDTO dto) {
		if (dto != null) {
			return new UsernamePasswordAuthenticationToken(dto.getPrincipal(), dto.getCredentials(),
					dto.getAuthorities());
		}
		return null;
	}
}
