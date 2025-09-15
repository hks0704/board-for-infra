#!/bin/bash

# 사용자에게 MySQL 삭제 확인 메시지 표시
echo "MySQL 클린 삭제를 진행합니다. 모든 MySQL 데이터가 제거됩니다."
# -r : raw 옵션, 이스케이프 /를 그대로 출력할 때 사용
read -rp "정말로 MySQL을 클린 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다. (y/n): " confirm

if [[ $confirm == [yY]] || [ $confirm == [yY][eE][sS]]]; then
    echo "MySQL 클린 삭제를 시작합니다..."

    # MySQL 서비스 중지
    sudo systemctl stop mysql

    # MySQL 패키지 및 관련 패키지 제거
    # --purge : 패키지 뿐만 아니라 사용자 옵션까지 지움
    sudo apt-get remove --purge -y mysql-server mysql-client mysql-common mysql-server-core-* mysql-client-core-*
    # 의존성 때문에 설치되었지만 지금 쓰지 않는 패키지 삭제
    sudo apt-get autoremove -y
    sudo apt-get autoclean -y

    # MySQL 설정 파일 및 데이터베이스 디렉토리 삭제
    sudo rm -rf /etc/mysql /var/lib/mysql

    # MySQL 로그 파일 삭제
    sudo rm -rf /var/log/mysql

    # MySQL 사용자 및 그룹 삭제 (선택적)
    sudo deluser mysql
    sudo delgroup mysql

    echo "MySQL이 시스템에서 완전히 제거되었습니다."
else
    cho "MySQL 클린 삭제가 취소되었습니다."
fi
