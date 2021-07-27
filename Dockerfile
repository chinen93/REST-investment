FROM openjdk:8-jdk-alpine
ADD target/rest-service-0.0.1-SNAPSHOT.jar rest_investment.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "rest_investment.jar"]

