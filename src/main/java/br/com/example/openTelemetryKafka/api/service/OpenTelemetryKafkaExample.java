package br.com.example.openTelemetryKafka.api.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.ByteArraySerializer;

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;

public class OpenTelemetryKafkaExample {
    public static void main(String[] args) {
        // Initialize Kafka producer properties
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("bootstrap.servers", "localhost:29092");
        kafkaProps.setProperty("key.serializer", ByteArraySerializer.class.getName());
        kafkaProps.setProperty("value.serializer", ByteArraySerializer.class.getName());
        // Create Kafka producer
        KafkaProducer<byte[], byte[]> producer = new KafkaProducer<>(kafkaProps);
        
        // Initialize OpenTelemetry exporter
        OtlpGrpcSpanExporter exporter = OtlpGrpcSpanExporter.builder()
                .setEndpoint("localhost:55680")
                .build();

        // Initialize OpenTelemetry SDK with exporter and default span processor
        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(BatchSpanProcessor.builder(exporter).build())
                .build();
        
        OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .buildAndRegisterGlobal();

        // Create and start a new span
        openTelemetry.getTracer("my-tracer").spanBuilder("my-span").startSpan().end();

        // Flush and shutdown the OpenTelemetry SDK
        openTelemetry.getSdkTracerProvider().forceFlush();
        openTelemetry.getSdkTracerProvider().shutdown();

        // Close the Kafka producer
        producer.close();
    }
}

// export OpenTelemetry data via OTLP
// then use the Kafka Exporter (ou outro metodo mais atual) to write that data to a Kafka topic
// visualizar resultados do kafka via elasticsearch