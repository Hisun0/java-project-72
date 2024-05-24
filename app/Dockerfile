FROM openjdk:21-jdk

COPY . .
RUN ./gradlew shadowJar
CMD ["java", "-jar", "build/libs/app-1.0-SNAPSHOT-all.jar"]