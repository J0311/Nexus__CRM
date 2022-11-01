FROM openjdk: latest
RUN mkdir /app

COPY ${JAR_FILE} app.jar
RUN javac NexusApplication.java
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]