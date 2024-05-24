FROM openjdk:21-jdk

COPY build/libs/*.jar app.jar
COPY .env .env
ENTRYPOINT ["java", "-jar", "/app.jar"]