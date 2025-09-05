# OpenJDK 17을 포함하는 경량화된 베이스 이미지
FROM openjdk:17-jdk-buster

# 컨테이너 내부 작업 디렉토리 지정
WORKDIR /home/app

# 호스트 시스템의 SpringBoot JAR 파일을 컨테이너 내부 작업 디렉토리로 복사
COPY build/libs/*.jar app.jar

RUN apt-get update && \
    rm -rf /var/lib/apt/lists/* && \
    fc-cache -fv

# 컨테이너가 시작 할 때 실행될 명령어, 작업 디렉토리 /home/app/app.jar 파일 실행
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "./app.jar"]

# 컨테이너 8080 포트를 외부로 노출
EXPOSE 8080
