FROM openjdk:8
VOLUME /target
COPY target/api-gateway-service-*.jar api-gateway-service.jar
ENTRYPOINT ["java", "-jar", "/api-gateway-service.jar"]