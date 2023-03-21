package br.com.example.openTelemetryKafka.api.model.dto;

import java.util.UUID;

import br.com.example.openTelemetryKafka.api.model.enums.TypeMessage;
import lombok.Data;

@Data
public class MonitorDTO {
	
	private UUID id = UUID.randomUUID();
	
	private UUID executionId;

	private Object integrationId;

	private String message;

	private Object sourcerId;

	private Object targetId;

	private Integer step;

	private String payloadPattern;

	private String errorTrace;

	private String errorMessage;

	private String first;

	private String last;

	private String selectedValue;

	private Long recordCount;

	private Long errorCount;

	private Object startedOn;

	private Object finishedOn;

	private Object occurrenceOn;
	
	private TypeMessage type;
	
}
