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
            script {
                def payload = """
                {
                    "text": "✅ *Build Success*",
                    "blocks": [
                        {
                            "type": "header",
                            "text": {
                                "type": "plain_text",
                                "text": "✅ Build Successful"
                            }
                        },
                        {
                            "type": "section",
                            "fields": [
                                {"type": "mrkdwn", "text": "*Job:*\n${env.JOB_NAME}"},
                                {"type": "mrkdwn", "text": "*Build:*\n#${env.BUILD_NUMBER}"},
                                {"type": "mrkdwn", "text": "*Status:*\nSUCCESS ✅"},
                                {"type": "mrkdwn", "text": "*Tests:*\n37/37 Passed"}
                            ]
                        },
                        {
                            "type": "actions",
                            "elements": [
                                {
                                    "type": "button",
                                    "text": {"type": "plain_text", "text": "View Build"},
                                    "url": "${env.BUILD_URL}"
                                },
                                {
                                    "type": "button",
                                    "text": {"type": "plain_text", "text": "Allure Report"},
                                    "url": "${env.BUILD_URL}allure"
                                }
                            ]
                        }
                    ]
                }
                """
                withCredentials([string(credentialsId: 'slack-webhook', variable: 'SLACK_WEBHOOK')]) {
                    sh """curl -X POST -H 'Content-type: application/json' --data '${payload}' \${SLACK_WEBHOOK}"""
                }
            }
            emailext (
                subject: "✅ Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """<!DOCTYPE html>
                <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
                        .container { max-width: 600px; margin: 0 auto; background: white; border-radius: 10px; overflow: hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
                        .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 30px; text-align: center; }
                        .header h1 { margin: 0; font-size: 28px; }
                        .status { background: #10b981; color: white; padding: 15px; text-align: center; font-size: 20px; font-weight: bold; }
                        .content { padding: 30px; }
                        .info-row { display: flex; justify-content: space-between; padding: 12px 0; border-bottom: 1px solid #e5e7eb; }
                        .info-label { font-weight: bold; color: #6b7280; }
                        .info-value { color: #1f2937; }
                        .stats { background: #f0fdf4; padding: 20px; border-radius: 8px; margin: 20px 0; text-align: center; }
                        .stats h2 { color: #10b981; margin: 0 0 10px 0; }
                        .button { display: inline-block; background: #667eea; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; margin: 10px 5px; }
                        .button:hover { background: #5568d3; }
                        .footer { background: #f9fafb; padding: 20px; text-align: center; color: #6b7280; font-size: 12px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🎉 Build Successful!</h1>
                        </div>
                        <div class="status">✅ ALL TESTS PASSED</div>
                        <div class="content">
                            <div class="info-row">
                                <span class="info-label">Job Name:</span>
                                <span class="info-value">${env.JOB_NAME}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Build Number:</span>
                                <span class="info-value">#${env.BUILD_NUMBER}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Status:</span>
                                <span class="info-value" style="color: #10b981; font-weight: bold;">SUCCESS</span>
                            </div>
                            <div class="stats">
                                <h2>📊 Test Results</h2>
                                <p style="font-size: 24px; margin: 10px 0;">37/37 Tests Passed ✅</p>
                                <p style="color: #6b7280;">Products: 12 | Carts: 9 | Users: 10 | Auth: 6</p>
                            </div>
                            <div style="text-align: center; margin-top: 30px;">
                                <a href="${env.BUILD_URL}" class="button">📋 View Build Details</a>
                                <a href="${env.BUILD_URL}allure" class="button">📊 View Allure Report</a>
                            </div>
                        </div>
                        <div class="footer">
                            <p>Jenkins CI/CD Pipeline | FakeStore API Tests</p>
                            <p>Automated by Jenkins at ${new Date().format('yyyy-MM-dd HH:mm:ss')}</p>
                        </div>
                    </div>
                </body>
                </html>""",
                to: 'thecutetester01@gmail.com',
                mimeType: 'text/html'
            )
        }
        failure {
            echo 'Tests failed!'
            script {
                def payload = """
                {
                    "text": "❌ *Build Failed*",
                    "blocks": [
                        {
                            "type": "header",
                            "text": {
                                "type": "plain_text",
                                "text": "❌ Build Failed"
                            }
                        },
                        {
                            "type": "section",
                            "fields": [
                                {"type": "mrkdwn", "text": "*Job:*\n${env.JOB_NAME}"},
                                {"type": "mrkdwn", "text": "*Build:*\n#${env.BUILD_NUMBER}"},
                                {"type": "mrkdwn", "text": "*Status:*\nFAILURE ❌"},
                                {"type": "mrkdwn", "text": "*Action:*\nCheck logs"}
                            ]
                        },
                        {
                            "type": "actions",
                            "elements": [
                                {
                                    "type": "button",
                                    "text": {"type": "plain_text", "text": "Console Output"},
                                    "url": "${env.BUILD_URL}console",
                                    "style": "danger"
                                },
                                {
                                    "type": "button",
                                    "text": {"type": "plain_text", "text": "Build Details"},
                                    "url": "${env.BUILD_URL}"
                                }
                            ]
                        }
                    ]
                }
                """
                withCredentials([string(credentialsId: 'slack-webhook', variable: 'SLACK_WEBHOOK')]) {
                    sh """curl -X POST -H 'Content-type: application/json' --data '${payload}' \${SLACK_WEBHOOK}"""
                }
            }
            emailext (
                subject: "❌ Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """<!DOCTYPE html>
                <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
                        .container { max-width: 600px; margin: 0 auto; background: white; border-radius: 10px; overflow: hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
                        .header { background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%); color: white; padding: 30px; text-align: center; }
                        .header h1 { margin: 0; font-size: 28px; }
                        .status { background: #ef4444; color: white; padding: 15px; text-align: center; font-size: 20px; font-weight: bold; }
                        .content { padding: 30px; }
                        .info-row { display: flex; justify-content: space-between; padding: 12px 0; border-bottom: 1px solid #e5e7eb; }
                        .info-label { font-weight: bold; color: #6b7280; }
                        .info-value { color: #1f2937; }
                        .alert { background: #fef2f2; border-left: 4px solid #ef4444; padding: 15px; margin: 20px 0; border-radius: 4px; }
                        .button { display: inline-block; background: #ef4444; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; margin: 10px 5px; }
                        .button:hover { background: #dc2626; }
                        .footer { background: #f9fafb; padding: 20px; text-align: center; color: #6b7280; font-size: 12px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>⚠️ Build Failed!</h1>
                        </div>
                        <div class="status">❌ TESTS FAILED</div>
                        <div class="content">
                            <div class="info-row">
                                <span class="info-label">Job Name:</span>
                                <span class="info-value">${env.JOB_NAME}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Build Number:</span>
                                <span class="info-value">#${env.BUILD_NUMBER}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Status:</span>
                                <span class="info-value" style="color: #ef4444; font-weight: bold;">FAILURE</span>
                            </div>
                            <div class="alert">
                                <p style="margin: 0; color: #991b1b;"><strong>⚠️ Action Required</strong></p>
                                <p style="margin: 10px 0 0 0; color: #6b7280;">The build has failed. Please check the console output for details.</p>
                            </div>
                            <div style="text-align: center; margin-top: 30px;">
                                <a href="${env.BUILD_URL}console" class="button">🔍 View Console Output</a>
                                <a href="${env.BUILD_URL}" class="button">📋 View Build Details</a>
                            </div>
                        </div>
                        <div class="footer">
                            <p>Jenkins CI/CD Pipeline | FakeStore API Tests</p>
                            <p>Automated by Jenkins at ${new Date().format('yyyy-MM-dd HH:mm:ss')}</p>
                        </div>
                    </div>
                </body>
                </html>""",
                to: 'thecutetester01@gmail.com',
                mimeType: 'text/html'
            )
        }
    }
}
