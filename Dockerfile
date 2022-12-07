# Build environment
FROM maven:alpine as build
WORKDIR /app
COPY pom.xml pom.xml
ADD ./src src/

#Package jar file
RUN mvn package


FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /app/target/itjapanese-1.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT java -jar /app/app.jar
