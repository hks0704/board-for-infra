#!/bin/bash

IMAGE_NAME="server/frontend"
CONTAINER_NAME="server-frontend"
CONTAINER_ID=$(docker ps -aqf "name=$CONTAINER_NAME")
PROJECT_NAME="board"

echo -e "\n<<<<<<<<<< Frontend Deploy Process Start >>>>>>>>>>\n"

echo ">>> CURRENT DOCKER INFORMATION:"
echo ">>> DOCKER CONTAINER NAME: $CONTAINER_NAME"
echo ">>> DOCKER CONTAINER ID: $CONTAINER_ID"
echo ""
# Stop & Remove Existing Container
echo ">>> DOCKER CONTAINER $CONTAINER_NAME 존재 여부 검사 시작..."
if [ ! -z "$CONTAINER_ID" ]; then
    echo ">>> DOCKER CONTAINER $CONTAINER_NAME 존재 확인."
    echo ">>> DOCKER CONTAINER $CONTAINER_NAME 중지 시작..."
    docker stop $CONTAINER_ID || {
        echo ">>> DOCKER CONTAINER $CONTAINER_NAME 중지 실패."
        exit 1
    }
    echo ">>> DOCKER CONTAINER $CONTAINER_NAME 중지 완료."
    
    echo ">>> DOCKER CONTAINER $CONTAINER_NAME 삭제 시작..."
    docker rm -f $CONTAINER_ID || {
        echo ">>> DOCKER CONTAINER $CONTAINER_NAME 삭제 실패."
        exit 1
    }
    echo ">>> DOCKER CONTAINER $CONTAINER_NAME 삭제 완료."
fi
echo ">>> DOCKER CONTAINER $CONTAINER_NAME 존재 여부 검사 완료."
echo ""

# Run Docker Container
echo ">>> DOCKER CONTAINER $CONTAINER_NAME 실행 시작..."
# -p 3000:3000 외부 3000 포트에서 받은 요청을 컨테이너 내부 3000 포트로 보냄
docker run -d -p 3000:3000 \
    --name $CONTAINER_NAME $IMAGE_NAME || {
        echo ">>> DOCKER IMAGE $IMAGE_NAME 실행 실패"
        exit 1
}
echo ">>> DOCKER CONTAINER $CONTAINER_NAME 실행 완료."
echo ""

# Copy Static Build Directory from Docker Container to Host
echo ">>> 빌드 정적파일 복사 시작..."
# -p : 상위 디렉토리가 없을 경우 자동 생성 옵션
mkdir -p /var/jenkins_home/application
docker cp $CONTAINER_NAME:/home/app/dist /var/jenkins_home/application || {
    echo ">>> 빌드 정적파일 복사 실패."
    exit 1
}
echo ">>> 빌드 정적 파일 복사 완료: /var/jenkins_home/application/dist"
echo ""

echo -e "\n<<<<<<<<<< Frontend Deploy Complete Successfully >>>>>>>>>>\n"