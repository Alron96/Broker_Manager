FROM openjdk:17-jdk-slim as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM openjdk:17-jdk-slim
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
EXPOSE 8484
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]