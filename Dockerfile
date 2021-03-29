FROM openjdk:11
ADD target/flight-0.0.1-SNAPSHOT.jar flight-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "flight-0.0.1-SNAPSHOT.jar"]