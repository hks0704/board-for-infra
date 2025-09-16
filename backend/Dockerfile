FROM openjdk:17-jdk-buster
WORKDIR /home/app
COPY build/libs/*.jar app.jar
RUN apt-get update && apt-get install -y fontconfig && \
    rm -rf /var/lib/apt/lists/* && \
    fc-cache -fv
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "./app.jar"]
EXPOSE 8080
