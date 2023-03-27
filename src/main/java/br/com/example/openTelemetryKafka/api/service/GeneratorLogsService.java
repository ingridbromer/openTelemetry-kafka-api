package br.com.example.openTelemetryKafka.api.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.example.openTelemetryKafka.api.model.dto.MonitorDTO;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

@Service
public class GeneratorLogsService {

	private static final Logger logger = LogManager.getLogger(GeneratorLogsService.class);

	public void generatorLogsToTopicTeste() {
		Tracer tracer = GlobalOpenTelemetry.getTracer("io.example.MinimalApp");
		Span span = tracer.spanBuilder("minimalSpan").startSpan();
		span.addEvent("Hello, OpenTelemetry!");
		span.end();
		
		logger.info("TESTE1");
		logger.info("TESTE2");
		logger.info("TESTE3");
		logger.error("TESTE_ERRO");
	}

	public MonitorDTO testError() {
		MonitorDTO monitor = null;
		monitor.getExecutionId();
		return monitor;
	}
}
