FROM openjdk:21-jdk

COPY . .
RUN ./gradlew shadowJar
CMD ["java", "-jar", "app-1.0-SHAPSHOT-all.jar"]