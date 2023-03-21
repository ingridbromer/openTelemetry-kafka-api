package br.com.example.openTelemetryKafka.api.service;

/*import java.util.logging.LogRecord;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.example.openTelemetryKafka.api.config.KafkaProducerConfig;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.export.SpanExporter;

public class KafkaExporter {
	
	@Autowired
	KafkaProducerConfig producerConfig;
	
	KafkaProducer<String, LogRecord> kafkaProducer = new KafkaProducer<>(producerConfig.producerConfigs());

	SpanExporter exporter =
	  KafkaLogExporter.newBuilder()
	    .setProducer(kafkaProducer)
	    .setTopic("my-topic")
	    .build();

	LoggingConfiguration loggingConfiguration = LoggingConfiguration.builder()
	    .addExporter(exporter)
	    .build();

	OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
	    .setTracerProvider(DefaultTracerProvider.builder().build())
	    .setMeterProvider(DefaultMeterProvider.builder().build())
	    .setLoggingConfiguration(loggingConfiguration)
	    .buildAndRegisterGlobal();


}*/
