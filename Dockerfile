FROM gradle:8.5-jdk21 AS build

WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .

RUN gradle build --no-daemon

FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]