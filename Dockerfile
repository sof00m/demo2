FROM maven:3.8.7-openjdk-18-slim AS build
COPY . /.
RUN mvn package


FROM openjdk:23-jdk-slim
COPY --from=build /target/*.jar /app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
