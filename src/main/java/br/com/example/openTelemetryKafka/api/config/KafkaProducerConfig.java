package br.com.example.openTelemetryKafka.api.config;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentracing.contrib.kafka.TracingProducerInterceptor;

@Configuration
public class KafkaProducerConfig {

	// Boiler plate para criação do Producer do Kafka

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${app.output-kafka-topic}")
	private String outputKafkaTopic;

	@Value("${app.kafka-topic-num-partitions}")
	private int kafkaTopicNumPartitions;

	@Value("${app.kafka-topic-replication-factor}")
	private short kafkaTopicReplicationFactor;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, TracingProducerInterceptor.class.getName());
        return props;
    }
	@Bean
	public ProducerFactory<String, SpanData> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, SpanData> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public NewTopic topic() {
		return new NewTopic(outputKafkaTopic, kafkaTopicNumPartitions, kafkaTopicReplicationFactor);
	}
	
}
