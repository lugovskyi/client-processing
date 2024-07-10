FROM openjdk:17-jdk-slim
WORKDIR /client-data-service
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar client-data-service.jar

ENTRYPOINT ["java", "-jar", "client-data-service.jar"]