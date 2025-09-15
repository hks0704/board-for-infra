#!/bin/bash
set -euo pipefail

IMAGE_NAME="server/jenkins"
CONTAINER_NAME="server-jenkins"

log() { echo -e "\033[1;32m>>> $1\033[0m"; }

IMAGE_ID=$(sudo docker images -q $IMAGE_NAME || true)
CONTAINER_ID=$(sudo docker ps -aqf "name=$CONTAINER_NAME" || true)

log "현재 Docker 상태 확인..."
# IMAGE_ID가 null이면 <없음>을 반환, IMAGE_ID는 변하지 않음
echo "$IMAGE_NAME IMAGE_ID: ${IMAGE_ID:-<없음>}"
echo "$CONTAINER_NAME CONTAINER_ID: ${CONTAINER_ID:-<없음>}"

# : -n : 비어있지 않음
if [ -n "$CONTAINER_ID" ]; then
    log "$CONTAINER_NAME 중지 및 삭제..."
    sudo docker stop $CONTAINER_ID || true
    sudo docker rm $CONTAINER_ID || true
fi

if [ -n "$IMAGE_ID" ]; then
    log "$IMAGE_NAME 이미지 삭제..."
    sudo docker rmi $IMAGE_ID || true
fi

# id : 현재 로그인한 사용자 확인
# -u : --user, effective user만 확인
USER_UID=$(id -u $USER)
# getent group docker : 도커 그룹 확인
# cut : 문자열 자르기 명령어
# -d[구분자] : 구분자 지정 옵션
# -f3 : 구분자로 잘린 3번째 내용을 표시
DOCKER_GID=$(getent group docker | cut -d: -f3)

log "Docker 이미지 빌드..."
# --build-arg : 도커 내에서 사용할 빌드 전용 환경변수
sudo docker build -t $IMAGE_NAME . \
    --build-arg USER_UID=$USER_UID \
    --build-arg DOCKER_GID=$DOCKER_GID

log "Jenkins 컨테이너 실행..."
# -R : 지정된 디렉토리 아래 모든 재용을 재귀적으로 포함
# user:group
sudo chown -R 1000:1000 /var/jenkins_home
sudo docker run -d \
    -p 8081:8080 -p 50000:50000 \
    -v /var/jenkins_home:/var/jenkins_home \
    -v /var/run/docker.sock:/var/run/docker.sock \
    --name $CONTAINER_NAME $IMAGE_NAME