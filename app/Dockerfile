FROM openjdk:21-jdk

COPY . .
RUN ./gradlew shadowJar
RUN ls -a
COPY ./build/libs/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]