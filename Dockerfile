FROM openjdk:8-jdk-alpine
COPY target/*.jar app.jar
COPY setup.sql /docker-entrypoint-initdb.d/
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080