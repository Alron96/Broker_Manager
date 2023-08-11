FROM openjdk:17.0.2-jdk-oracle
EXPOSE 8484
VOLUME /tmp
COPY /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
