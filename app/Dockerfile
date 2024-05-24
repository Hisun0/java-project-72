FROM openjdk:21-jdk

COPY . .
RUN ./gradlew shadowJar
RUN cd build/libs
CMD ["java", "-jar", "app-1.0-SHAPSHOT-all.jar"]