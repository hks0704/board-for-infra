#!/bin/bash
set -euo pipefail

# ===== 설정 =====
ENV_FILE="./mysql.env"
if [ -f "$ENV_FILE" ]; then # -f : 파일이 존재하며 디렉토리가 아닐 때 true
    # 환경 변수 파일에서 로드
    source "$ENV_FILE" # source : 특정파일의 변수, 함수를 include
else
    # 사용자 입력으로 받기
    # -s : 민감한 정보는 입력시 표시되지 않게
    # -p : 프롬프트 문자열 출력 후 입력
    read -sp "Root Password: " ROOT_PASSWORD
    echo
    read -sp "New Username: " NEW_USERNAME
    echo
    read -sp "New Password: " NEW_PASSWORD
    echo
fi

MYSQL_CNF="/etc/mysql/mysql.conf.d/mysqld.cnf"

# ===== log 출력 함수 =====
log() {
    echo -e "\033[1;32m[INFO]\033[0m $1"
}

# ===== 방화벽 설정 =====
# -q : 메시지 출력 안함
# -w : 단어 단위로 매칭되는 라인 출력
if sudo ufw status | grep -qw inactive; then
    echo "방화벽이 비활성화되어 있습니다. 방화벽을 활성화합니다."
    sudo ufw enable
fi
sudo ufw allow 3306

# ===== MySQL 설치 =====
log "MySQL 설치를 시작합니다..."
sudo apt-get update -y
sudo apt-get install -y mysql-server

# ===== MySQL 서비스 시작 =====
log "MySQL 서비스 시작..."
sudo systemctl start mysql
sudo systemctl enable mysql

# ===== MySQL 사용자 및 권한 설정 =====
log "루트 비밀번호 및 사용자 설정..."
sudo mysql <<EOF
ALTER USER 'root'@'localhost' IDENTIFIED BY '${ROOT_PASSWORD}';
CREATE USER IF NOT EXISTS '${NEW_USERNAME}'@'%' IDENTIFIED BY '${NEW_PASSWORD}';
GRANT ALL PRIVILEGES ON *.* TO '${NEW_USERNAME}'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
EOF

# ===== bind-address 수정 =====
if grep -q "bind-address" "$MYSQL_CNF"; then
    sudo sed -i 's/^bind-address.*/bind-address = 0.0.0.0/' "$MYSQL_CNF"
else
    # tee : 출력 내용을 파일에 저장하고 싶을 때
    # -a : 파일에 덮어쓰지 않고 이어쓰는 옵션
    echo "bind-address = 0.0.0.0" | sudo tee -a "$MYSQL_CNF"
fi

log "MySQL 서비스 재시작..."
sudo systemctl restart mysql

log "MySQL 설치 및 사용자 설정이 완료되었습니다."