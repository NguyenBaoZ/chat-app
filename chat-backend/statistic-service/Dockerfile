FROM openjdk:23-jdk
WORKDIR /app
COPY target/statistic-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xmx256m", "-Xms128m", "-jar", "app.jar"]
