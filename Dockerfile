FROM openjdk:18
COPY target/tennis-0.0.1-SNAPSHOT.jar tennis-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/tennis-0.0.1-SNAPSHOT.jar"]