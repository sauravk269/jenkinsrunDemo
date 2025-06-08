pipeline {
    agent any
    stages {
         stage('Checkout Code') {
                    steps {
                    echo 'Checking out source code...'
                         git credentialsId: 'github-creds', // ID from Jenkins credentials
                         url: 'https://github.com/your-username/your-repo.git',
                         branch: 'main' // or 'master' or any other branch
                    }
                }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
