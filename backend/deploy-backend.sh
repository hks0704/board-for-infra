#!/bin/bash
set -euo pipefail

IMAGE_NAME="server/backend"
CONTAINER_NAME="server-backend"

# Docker에서 이미지, 컨테이너 ID 조회
IMAGE_ID=$(docker images -q "${IMAGE_NAME}" || true)
CONTAINER_ID=$(docker ps -aqf "name=${CONTAINER_NAME}" || true)

# ANSI Color
GREEN="\033[0;32m"
RED="\033[0;31m"
YELLOW="\033[1;33m"
NC="\033[0m" # No Color

log() {
  echo -e "${YELLOW}>>> $1${NC}"
}

error_exit() {
  echo -e "${RED}!! $1${NC}"
  exit 1
}

remove_container() {
  if [ -n "${CONTAINER_ID}" ]; then
    log "컨테이너 ${CONTAINER_NAME} 존재. 중지 및 삭제..."
    docker stop ${CONTAINER_ID} || error_exit "컨테이너 중지 실패"
    docker rm -f ${CONTAINER_ID} || error_exit "컨테이너 삭제 실패"
    log "컨테이너 삭제 완료"
  fi
}

run_container() {
  log "도커 컨테이너 ${CONTAINER_NAME} 실행 시작..."
  docker run -d -p 8080:8080 \
  -v /home/ubuntu/uploads:/upload \
  --name $CONTAINER_NAME $IMAGE_NAME || error_exit "실행 실패."
  log "실행 완료."
}

echo -e "\n<<<<<<<<<< ${GREEN}Backend Deploy Process Start${NC} >>>>>>>>>>\n"
log "도커 컨테이너 이름: ${CONTAINER_NAME}"
log "도커 컨테이너 ID: ${CONTAINER_ID}"

remove_container
run_container

echo -e "\n<<<<<<<<<< ${GREEN}Backend Deploy Complete Successfully${NC} >>>>>>>>>>\n"
