# Build environment
FROM maven:alpine as build
WORKDIR /app
COPY pom.xml pom.xml
ADD ./src src/

#Package jar file
RUN mvn package


FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

ENTRYPOINT ["java","-jar", "/app/app.jar"]
