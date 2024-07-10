FROM openjdk:17-jdk-slim
WORKDIR /auth-service
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar auth-service.jar

EXPOSE  8001
ENTRYPOINT ["java", "-jar", "auth-service.jar", "-P"]