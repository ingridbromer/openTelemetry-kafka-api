package br.com.example.openTelemetryKafka.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.protobuf.util.JsonFormat;

import io.opentelemetry.proto.trace.v1.ResourceSpans;

@Service
public class KafkaProtobufJsonConverter {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    

    private final JsonFormat.Printer PRINTER = JsonFormat.printer().includingDefaultValueFields();

    @KafkaListener(topics = "input-topic", groupId = "protobuf-json-converter")
    public void processMessage(byte[] messageBytes) {
    	
        try {
        	  ResourceSpans resourceSpans = ResourceSpans.parseFrom(messageBytes);
              String json = PRINTER.print(resourceSpans);
              kafkaTemplate.send("output-topic", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
