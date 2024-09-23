FROM dvmarques/openjdk-17-jdk-alpine-with-timezone
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]