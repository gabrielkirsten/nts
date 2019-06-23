FROM openjdk:8
VOLUME /target
COPY target/customer-campaign-service-*.jar customer-campaign-service.jar
ENTRYPOINT ["java", "-jar", "/customer-campaign-service.jar"]