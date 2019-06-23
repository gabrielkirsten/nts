FROM openjdk:8
VOLUME /target
COPY target/team-service-*.jar team-service.jar
ENTRYPOINT ["java", "-jar", "/team-service.jar"]