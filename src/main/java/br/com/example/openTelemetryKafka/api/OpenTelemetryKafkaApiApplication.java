package br.com.example.openTelemetryKafka.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.example.openTelemetryKafka.api.service.GeneratorLogsService;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

@SpringBootApplication
public class OpenTelemetryKafkaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenTelemetryKafkaApiApplication.class, args);
		// initOpenTelemetry();
		generateLog();

	}

	private static final Logger logger = LogManager.getLogger(OpenTelemetryKafkaApiApplication.class);

	/*
	 * private static final String OTEL_COLLECTOR_ENDPOINT =
	 * "http://collector:4317"; public static void initOpenTelemetry() {
	 * OtlpGrpcSpanExporter spanExporter =
	 * OtlpGrpcSpanExporter.builder().setEndpoint(OTEL_COLLECTOR_ENDPOINT).build();
	 * 
	 * BatchSpanProcessor spanProcessor =
	 * BatchSpanProcessor.builder(spanExporter).build();
	 * 
	 * SdkTracerProvider tracerProvider =
	 * SdkTracerProvider.builder().addSpanProcessor(spanProcessor).build();
	 * 
	 * OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).
	 * buildAndRegisterGlobal();
	 * 
	 * }
	 */
	
	private static void generateLog() {
		Tracer tracer = GlobalOpenTelemetry.getTracer("io.example.MinimalApp");
		Span span = tracer.spanBuilder("minimalSpan").startSpan();
		span.addEvent("Hello, OpenTelemetry!");
		span.end();

		logger.info("TESTE1");
		logger.info("TESTE2");
		logger.info("TESTE3");
	}

}
