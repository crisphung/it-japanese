FROM openjdk:8-jdk-alpine

WORKDIR /app

VOLUME /tmp

ADD /target/itjapanese-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

