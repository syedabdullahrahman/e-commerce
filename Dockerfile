FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY target/*.jar app.jar
COPY target/classes/application.properties application.properties

CMD ["java", "-jar", "app.jar"]
