
# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/Calculator-1.0-SNAPSHOT.jar app.jar
COPY src/main/resources/config.yaml config.yaml

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "server", "config.yaml"]
