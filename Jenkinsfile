pipeline {
    agent any
    
    tools {
        maven 'Maven'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Kalisha1234/Automation-Test-Execution-with-Jenkins-CI-CD-Completion-requirements.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Generate Allure Report') {
            steps {
                sh 'mvn allure:report'
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
            emailext (
                subject: "✅ Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """<h2>Build Successful!</h2>
                         <p><b>Job:</b> ${env.JOB_NAME}</p>
                         <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                         <p><b>Status:</b> SUCCESS</p>
                         <p><b>Tests:</b> All 37 tests passed ✅</p>
                         <p><a href='${env.BUILD_URL}'>View Build</a></p>
                         <p><a href='${env.BUILD_URL}allure'>View Allure Report</a></p>""",
                to: 'thecutetester01@gmail.com',
                mimeType: 'text/html'
            )
        }
        failure {
            echo 'Tests failed!'
            emailext (
                subject: "❌ Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """<h2>Build Failed!</h2>
                         <p><b>Job:</b> ${env.JOB_NAME}</p>
                         <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                         <p><b>Status:</b> FAILURE</p>
                         <p>Check the console output for details.</p>
                         <p><a href='${env.BUILD_URL}console'>View Console Output</a></p>""",
                to: 'thecutetester01@gmail.com',
                mimeType: 'text/html'
            )
        }
    }
}
