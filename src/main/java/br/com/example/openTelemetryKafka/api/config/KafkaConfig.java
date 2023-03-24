package br.com.example.openTelemetryKafka.api.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
	 @Bean
	    public ConsumerFactory<String, byte[]> consumerFactory() {
	        Map<String, Object> props = new HashMap<>();
	        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        props.put(ConsumerConfig.GROUP_ID_CONFIG, "protobuf-json-converter");
	        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
	        return new DefaultKafkaConsumerFactory<>(props);
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }

	    @Bean
	    public ProducerFactory<String, String> producerFactory() {
	        Map<String, Object> props = new HashMap<>();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        return new DefaultKafkaProducerFactory<>(props);
	    }

	    @Bean
	    public KafkaTemplate<String, String> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }
	}
