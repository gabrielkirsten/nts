FROM openjdk:8
VOLUME /target
COPY target/customer-service-*.jar customer-service.jar
ENTRYPOINT ["java", "-jar", "/customer-service.jar"]