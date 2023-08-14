FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /opt/broker
#COPY .mvn/ .mvn
COPY pom.xml ./
#RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN mvn clean install
#RUN ./mvnw clean install
FROM maven:3.8.4-openjdk-17
WORKDIR /opt/broker
COPY --from=builder /opt/broker/target/*.jar /opt/broker/*.jar
EXPOSE 8484
ENTRYPOINT ["java","-Dspring.profiles.active=deploy", "-jar", "/opt/broker/*.jar"]