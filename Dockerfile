FROM openjdk:17
EXPOSE 8080
COPY target/microservices.jar microservices.jar
ENTRYPOINT ["java", "-jar", "/microservices.jar"]
