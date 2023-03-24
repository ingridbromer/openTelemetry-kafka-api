package br.com.example.openTelemetryKafka.api.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GeneratorLogsService {
	

	 private static final Logger logger = LogManager.getLogger(GeneratorLogsService.class);
	
	 public void generatorLogsToTopicTeste() {
		 logger.info("TESTE1");
		 logger.info("TESTE2");
		 logger.info("TESTE3");
	 }
}
