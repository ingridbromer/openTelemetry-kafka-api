package br.com.example.openTelemetryKafka.api.service;

import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.autoconfigure.spi.traces.ConfigurableSpanExporterProvider;
import io.opentelemetry.sdk.trace.export.SpanExporter;

public class KafkaSpanExporterFactory implements ConfigurableSpanExporterProvider{
    @Override
    public SpanExporter createExporter(ConfigProperties config) {
        String bootstrapServers = config.getString("broker:29092");
        String topic = config.getString("teste");
        return new FileExporter(bootstrapServers, topic);
    }

    @Override
    public String getName() {
        return "kafka";
    }
}
