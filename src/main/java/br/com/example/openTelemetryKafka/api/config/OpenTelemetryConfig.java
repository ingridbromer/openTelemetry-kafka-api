package br.com.example.openTelemetryKafka.api.config;

import org.springframework.context.annotation.Configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

public class OpenTelemetryConfig {

	 public static void initOpenTelemetry(String collectorEndpoint) {
	        ManagedChannel channel = ManagedChannelBuilder.forTarget(collectorEndpoint)
	                                                      .usePlaintext()
	                                                      .build();
	        OtlpGrpcSpanExporter exporter = OtlpGrpcSpanExporter.builder()
	                                                             .setChannel(channel)
	                                                             .build();
	        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
	                                                                .addSpanProcessor(SimpleSpanProcessor.create(exporter))
	                                                                .build();
	        OpenTelemetrySdk.builder()
	                        .setTracerProvider(sdkTracerProvider)
	                        .buildAndRegisterGlobal();
	    }
	}
