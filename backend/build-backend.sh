#!/bin/bash
# set -o pipefail : 파이프라인 실행결과 체크(하나라도 오류 발생하면 오류로 간주)
# -e : 실행 중 에러가 발생하면 즉시 종료
# -u : 선언되지 않은 변수 사용시 오류 처리
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

remove_image() {
  if [ -n "${IMAGE_ID}" ]; then
    log "이미지 ${IMAGE_NAME} 존재. 삭제 시작..."
    docker rmi -f ${CONTAINER_ID} || error_exit "이미지 삭제 실패"
    log "컨테이너 삭제 완료"
  fi
}

build_image() {
  log "이미지 ${IMAGE_NAME} 빌드 시작..."
  docker build -t "${IMAGE_NAME}" . || error_exit "이미지 빌드 실패"
  log "이미지 빌드 완료."
}

echo -e "\n<<<<<<<<<< ${GREEN}Backend Build Process Start${NC} >>>>>>>>>>\n"
log "이미지 이름: ${IMAGE_NAME}"
log "컨테이너 이름: ${CONTAINER_NAME}"

remove_container
remove_image
build_image

echo -e "\n<<<<<<<<<< ${GREEN}Backend Build Complete Successfully${NC} >>>>>>>>>>\n"
