FROM openjdk:8-jdk-alpine

WORKDIR /app

VOLUME /tmp

COPY ./target/itjapanese-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","./app.jar"]

