pipeline {
    agent any
    stages {
         stage('Checkout Code') {
                    steps {
                    echo 'Checking out source code...'
                         git credentialsId: 'credentialsId', // ID from Jenkins credentials
                         url: 'https://github.com/sauravk269/jenkinsrunDemo.git',
                         branch: 'master' // or 'master' or any other branch
                    }
         }
          stage('Install Dependencies') {
                            steps {
                                echo 'Installing Maven dependencies...'
                                sh 'mvn clean compile'
                            }
          }
          stage('Run Tests') {
                      steps {
                          sh 'mvn test'
                      }
          }
           stage('Generate Report') {
                      steps {
                          echo 'Archiving Extent Report...'
                          publishHTML([
                              allowMissing: false,
                              alwaysLinkToLastBuild: true,
                              keepAll: true,
                              reportDir: 'reports',
                              reportFiles: 'ExtentReport.html',
                              reportName: 'Extent HTML Report'
                          ])
                      }
           }
    }
    post {
            always {
                junit '**/target/surefire-reports/*.xml'
            }
        }

}
