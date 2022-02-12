FROM openjdk:11-jdk-slim
VOLUME /main-app
ADD target/account-manager-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]
