package br.com.example.openTelemetryKafka.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GeneratorLogsService {
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());

	 
	 public void generatorLogsToTopicTeste() {
		 logger.info("TESTE1");
		 logger.info("TESTE2");
		 logger.info("TESTE3");
	 }
}
