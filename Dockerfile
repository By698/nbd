FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app/main.jar
ADD src/main/resources/application.properties /app/application.properties
ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom --spring.config.location=classpath:file:/app/application-properties","-jar","/app/main.jar"]
EXPOSE 8080