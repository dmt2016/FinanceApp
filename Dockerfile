FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /FinApp
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk
WORKDIR /FinApp
COPY --from=build FinApp/target/FinApp.jar .
CMD ["java", "-jar", "FinApp.jar"]