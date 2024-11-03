FROM maven:3.9.6-eclipse-temurin-22-alpine AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM openjdk:22-ea-1-jdk-slim
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
ENV JAVA_HOME=/usr/local/openjdk-22
EXPOSE 8080
RUN mkdir /app
COPY --from=build /usr/src/app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]