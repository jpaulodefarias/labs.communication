FROM gradle:6-jdk11 AS build
WORKDIR /app
COPY . /app/
RUN gradle bootJar --quiet --no-daemon --parallel

FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=/app/build/libs/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
