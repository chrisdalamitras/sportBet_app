FROM openjdk:17-jdk-alpine

COPY target/sportBet-0.0.1-SNAPSHOT.jar sportBet_app.jar

ENTRYPOINT [ "java", "-jar", "sportBet_app.jar" ]