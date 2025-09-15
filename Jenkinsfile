// 공통 함수 정의
pipeline {
    agent any

    // 필요한 변수 설정
    environment {
        PROJECT_DIR = '/server'

        // 사설 도커 레지스트리 정보 추가
        DOCKER_REGISTRY = 'localhost:5000'

        BACKEND_IMAGE_NAME = 'server/backend'
        FRONTEND_IMAGE_NAME = 'server/frontend'
        BACKEND_CONTAINER_NAME = 'server-backend'
        FRONTEND_CONTAINER_NAME = 'server-frontend'
    }


    stages {
        stage('Checkout') {
            steps {
                echo 'Starting Repository Checkout'
                echo '<<<<<<<<<< Checkout Repository Start >>>>>>>>>>'

                git branch: 'main', credentialsId: 'GITHUB_ACCESS_TOKEN',
                url: 'https://github.com/hks0704/board-for-infra'

                echo '<<<<<<<<<< Checkout Repository Complete Successfully >>>>>>>>>>'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean build'

                    sh 'chmod +x ./build-backend.sh'
                    sh './build-backend.sh'
                }
            }
            // post : 파이프라인이 모두 끝났을 때 실행되는 작업
            post {
                // success, failure : 각각 성공, 실패 상태로 파이프라인이 끝났을 때 실행되는 작업
                success {
                    echo 'BE/빌드, Success'
                }
                failure {
                    echo 'BE/빌드, Failed'
                }
            }
        }

        stage('Test Backend') {
            steps {
                dir('backend') {
                    echo '<<<<<<<<<< Backend Tests Start >>>>>>>>>>'
                    sh './gradlew test'
                    echo '<<<<<<<<<< Backend Tests Complete Successfully >>>>>>>>>>'
                }
            }
            post {
                success {
                    echo 'BE/테스트, Success'
                }
                failure {
                    echo 'BE/테스트, Failed'
                }
            }
        }

        stage('Deploy Backend') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./deploy-backend.sh'
                    sh './deploy-backend.sh'
                }
            }
            post {
                success {
                    echo 'BE/배포, Success'
                }
                failure {
                    echo 'BE/배포, Failed'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'chmod +x ./build-frontend.sh'
                    sh './build-frontend.sh'
                }
            }
            post {
                success {
                    echo 'FE/빌드, Success'
                }
                failure {
                    echo 'FE/빌드, Failed'
                }
            }
        }

        stage('Deploy Frontend') {
            steps {
                dir('frontend') {
                    sh 'chmod +x ./deploy-frontend.sh'
                    sh './deploy-frontend.sh'
                }
            }
            post {
                success {
                    echo 'FE/배포, Success'
                }
                failure {
                    echo 'FE/배포, Failed'
                }
            }
        }
    }

    post {
        always {
            // sh 'docker system prune -af'
            echo '>>>>>>>>>> Pipeline Execution Complete. <<<<<<<<<<'
        }
        success {
            echo '>>>>>>>>>> Pipeline Execution Success. <<<<<<<<<<'
        }
        failure {
            echo '>>>>>>>>>> Pipeline Execution Failed. <<<<<<<<<<'
        }
    }
}