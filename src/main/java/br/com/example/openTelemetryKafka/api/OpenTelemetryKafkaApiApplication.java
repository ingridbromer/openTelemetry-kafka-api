package br.com.example.openTelemetryKafka.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.example.openTelemetryKafka.api.config.OpenTelemetryConfig;

@SpringBootApplication
public class OpenTelemetryKafkaApiApplication {

	public static void main(String[] args) {
		String collectorEndpoint = "dns:///collector:4317";
		OpenTelemetryConfig.initOpenTelemetry(collectorEndpoint);
		SpringApplication.run(OpenTelemetryKafkaApiApplication.class, args);
	}

}
