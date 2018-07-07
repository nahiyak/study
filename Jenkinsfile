pipeline {
  agent {
    docker {
      image 'gradle:alpine'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'sh "gradlew clean build" '
      }
    }
  }
}