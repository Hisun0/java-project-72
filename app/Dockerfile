FROM openjdk:21-jdk

COPY . .
RUN ./gradlew shadowJar
ENTRYPOINT ["java", "-jar", "/app.jar"]