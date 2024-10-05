FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/*.jar elecciones-app.jar
ENTRYPOINT ["java","-jar","elecciones-app.jar"]