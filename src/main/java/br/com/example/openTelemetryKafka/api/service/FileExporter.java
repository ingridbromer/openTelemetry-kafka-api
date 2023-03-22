package br.com.example.openTelemetryKafka.api.service;

import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.export.SpanExporter;

public class FileExporter implements SpanExporter {
    private final Producer<String, String> producer;
    private final String topic;

    public FileExporter(String bootstrapServers, String topic) {
        this.topic = topic;
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props);
    }

    @Override
    public CompletableResultCode export(Collection<SpanData> spans) {
        try {
            for (SpanData span : spans) {
                String spanJson = convertSpanToJson(span); // Implement a method to convert SpanData to JSON or another serialization format
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, spanJson);
                producer.send(record);
            }
            producer.flush();
            return CompletableResultCode.ofSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableResultCode.ofFailure();
        }
    }
    
	private String convertSpanToJson(SpanData span) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(span);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

    @Override
    public void close() {
        producer.close();
    }

	@Override
	public CompletableResultCode flush() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableResultCode shutdown() {
		// TODO Auto-generated method stub
		return null;
	}
}
