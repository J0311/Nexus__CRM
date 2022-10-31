FROM openjdk: latest
RUN mkdir /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]