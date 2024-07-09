FROM openjdk:17-jdk-slim
WORKDIR /auth-service
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar auth-service.jar

#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auth-service.jar"]