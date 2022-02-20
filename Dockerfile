FROM openjdk:17-alpine

COPY target/Rep*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]