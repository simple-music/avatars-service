FROM openjdk:11.0.1-jdk as base
WORKDIR /tmp
COPY . .
RUN ./gradlew build

FROM openjdk:11.0.1-jre-slim
WORKDIR /tmp
COPY --from=base /tmp/build/libs/*.jar .
COPY ./default.jpg /var/www/img/default.jpg
ENTRYPOINT java -jar -Dspring.profiles.active=prod ./*.jar
EXPOSE 80
