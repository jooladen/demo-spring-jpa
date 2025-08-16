pipeline {
  agent any

  // 편의 옵션
  options {
    timestamps()
    skipDefaultCheckout(true)   // 중복 체크아웃 방지
  }

  // 로컬이라 웹훅 대신 폴링(원하면 주기 바꾸세요)
  triggers {
    pollSCM('H/2 * * * *')  // 대략 2분마다 변경 감지
  }

  // 위에서 지정한 Tools 이름과 일치해야 함
  tools {
    jdk   'JDK17'
    maven 'M3'
  }

  environment {
    // 네트워크 불안정시 Maven 전송 풀 이슈 방지용(선택)
    MAVEN_OPTS = '-Dmaven.wagon.http.pool=false'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build (package, skip tests)') {
      steps {
        // 설치/경로 확인용
        bat 'mvn -version'

        // 필요한 경우 -DskipTests 제거하고 Test 단계에서만 돌려도 됨
        bat 'mvn -B -U -DskipTests clean package'
      }
    }

    stage('Test') {
      steps {
        bat 'mvn -B test'
      }
      post {
        always {
          // 단일/멀티모듈 모두 커버
          junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Archive Artifacts') {
      steps {
        // 단일/멀티모듈 모두의 jar 수집
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, onlyIfSuccessful: true
      }
    }
  }

  post {
    success { echo "✅ ${env.JOB_NAME} #${env.BUILD_NUMBER} SUCCESS" }
    failure { echo "❌ ${env.JOB_NAME} #${env.BUILD_NUMBER} FAILED" }
  }
}
