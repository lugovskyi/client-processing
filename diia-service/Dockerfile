FROM openjdk:17-jdk-slim
WORKDIR /diia-service
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar diia-service.jar

ENTRYPOINT ["java", "-jar", "diia-service.jar"]