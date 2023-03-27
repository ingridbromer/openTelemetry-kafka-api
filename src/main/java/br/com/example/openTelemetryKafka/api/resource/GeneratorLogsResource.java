package br.com.example.openTelemetryKafka.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.openTelemetryKafka.api.model.dto.MonitorDTO;
import br.com.example.openTelemetryKafka.api.service.GeneratorLogsService;

@RestController
public class GeneratorLogsResource {

	@Autowired
	GeneratorLogsService service;
	
	@GetMapping("/generatorLogs")
	public void generatorLogs() {
		service.generatorLogsToTopicTeste();
	}
	
	@GetMapping("/testError")
	public MonitorDTO testError() {
		return service.testError();
	}
}
