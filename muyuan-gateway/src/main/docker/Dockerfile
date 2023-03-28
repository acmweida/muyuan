FROM openjdk:8-jdk-alpine
VOLUME /logs:/home/logs
ADD muyuan-gateway-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=comp","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]