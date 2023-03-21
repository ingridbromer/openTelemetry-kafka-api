package br.com.example.openTelemetryKafka.api.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.export.SpanExporter;

public class FileExporter implements SpanExporter {

	@Autowired
	KafkaPublisherService publisher;

	private BufferedWriter writer = null;

	public FileExporter(String filePath) throws IOException {
		writer = new BufferedWriter(new FileWriter(filePath));
	}

	@Override
	public CompletableResultCode export(Collection<SpanData> spans) {
		for (SpanData span : spans) {
			try {
				ProducerRecord<String, SpanData> record = new ProducerRecord<>("teste", span);
				publisher.publish(record);
			} catch (Exception e) {
				return CompletableResultCode.ofFailure();
			}
		}
		return CompletableResultCode.ofSuccess();
	}

	@Override
	public CompletableResultCode flush() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableResultCode shutdown() {
		// TODO Auto-generated method stub
		// producer.close();
		return null;
	}

}
