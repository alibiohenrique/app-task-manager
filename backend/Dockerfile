FROM openjdk:21-slim

WORKDIR /app

COPY target/task-app-0.0.1-SNAPSHOT.jar /app/spring-app.jar

ENTRYPOINT ["java", "-jar", "spring-app.jar"]