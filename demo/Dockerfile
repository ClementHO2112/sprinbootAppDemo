# Build stage
FROM maven:3.9.7-eclipse-temurin-17-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN --mount=type=cache,target=/root/.m2 mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

# Package stage
FROM openjdk:17-jdk-alpine
MAINTAINER DEMO
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]