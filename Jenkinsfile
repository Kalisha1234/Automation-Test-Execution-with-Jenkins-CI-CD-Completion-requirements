pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK11'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Kalisha1234/Automation-Test-Execution-with-Jenkins-CI-CD-Completion-requirements.git'
            }
        }
        
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        
        stage('Generate Allure Report') {
            steps {
                bat 'mvn allure:report'
            }
        }
        
        stage('Publish Reports') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'TestNG Report'
                ])
            }
        }
    }
    
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Tests passed successfully!'
            // emailext body: 'Tests passed successfully!', subject: 'Build Success', to: '<YOUR_EMAIL>'
            // slackSend channel: '#jenkins', message: "Build ${env.BUILD_NUMBER} passed!"
        }
        failure {
            echo 'Tests failed!'
            // emailext body: 'Tests failed. Check Jenkins for details.', subject: 'Build Failed', to: '<YOUR_EMAIL>'
            // slackSend channel: '#jenkins', message: "Build ${env.BUILD_NUMBER} failed!"
        }
    }
}
