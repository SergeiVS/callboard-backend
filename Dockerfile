FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
#RUN mvn clean package -DskipTests


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

CMD ["java", "-jar", "app.jar"]

ENTRYPOINT ["top", "-b"]