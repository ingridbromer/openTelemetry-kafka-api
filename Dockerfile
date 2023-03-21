COPY ./*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8097
# OpenTelemetry:
# https://github.com/open-telemetry/opentelemetry-java-instrumentation
ENV JAVA_OPTS \
  -Dotel.exporter=br.com.example.openTelemetryKafka.api.service.FileExporter \
  -Dotel.exporter.kafka.bootstrap.servers=localhost:29092 \
  -Dotel.exporter.kafka.topic=teste
  -javaagent:C:\confluent-7.0.1\opentelemetry-javaagent.jar \