package br.com.example.openTelemetryKafka.api.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.example.openTelemetryKafka.api.config.AppConfigProperties;
import br.com.example.openTelemetryKafka.api.exceptions.InvalidValueException;
import io.opentelemetry.sdk.trace.data.SpanData;


@Service
public class KafkaPublisherService {

	@Autowired
	private AppConfigProperties appProperties;
	
	@Autowired
	private KafkaTemplate<String, SpanData> kafkaTemplate;

	@Autowired
	private ObjectMapper myMapper;

	private static final Logger LOG = LoggerFactory.getLogger(KafkaPublisherService.class);

	public void publish(ProducerRecord<String, SpanData> record) throws JsonProcessingException, InvalidValueException {
		LOG.info("Sending '{}' to topic = '{}' ", record, appProperties.getOutputKafkaTopic());
			
		Message<String> message = MessageBuilder.withPayload(myMapper.writeValueAsString(record))
				.setHeader(KafkaHeaders.TOPIC, appProperties.getOutputKafkaTopic()).build();
		
		kafkaTemplate.send(message);
		}
	
}
