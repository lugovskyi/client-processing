FROM openjdk:17-jdk-slim
WORKDIR /edr-service
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar edr-service.jar

ENTRYPOINT ["java", "-jar", "edr-service.jar"]