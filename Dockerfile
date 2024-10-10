# Stage 1: Build the application with JDK 17
FROM maven:3.9.8-eclipse-temurin-22-alpine AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
RUN mv target/*.jar app.jar



# Stage 2: Run the application with JRE 17
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the built artifact from the build stage
COPY --from=build /app/*.jar app.jar
# Run the application
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]