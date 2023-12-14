FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} plantuml-plugin.jar
ENTRYPOINT ["java","-jar","/plantuml-plugin.jar"]