FROM openjdk:8
VOLUME /target
COPY target/naming-service-*.jar naming-service.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/naming-service.jar"]