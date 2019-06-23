FROM openjdk:8
VOLUME /target
COPY target/campaign-service-*.jar campaign-service.jar
ENTRYPOINT ["java", "-jar", "/campaign-service.jar"]