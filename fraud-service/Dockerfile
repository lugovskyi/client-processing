FROM openjdk:17-jdk-slim
WORKDIR /fraud-service
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar fraud-service.jar

ENTRYPOINT ["java", "-jar", "fraud-service.jar"]