# Automation Test Execution with Jenkins CI/CD

This project demonstrates automated API testing using REST Assured with Jenkins CI/CD pipeline integration for the FakeStore API.

## Project Overview

- **API Under Test**: https://fakestoreapi.com/
- **Testing Framework**: REST Assured + TestNG
- **Build Tool**: Maven
- **Reporting**: Allure Reports + TestNG Reports
- **CI/CD**: Jenkins Pipeline
- **Containerization**: Docker
- **Notifications**: Email (Gmail SMTP) + Slack

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Docker (optional)
- Jenkins (local or Docker)
- Git

## Project Structure

```
.
├── src/
│   └── test/
│       ├── java/org/example/
│       │   ├── base/BaseTest.java          # Base test configuration
│       │   ├── testdata/TestData.java      # Centralized test data
│       │   ├── products/ProductsTest.java  # Products API tests (12)
│       │   ├── carts/CartsTest.java        # Carts API tests (9)
│       │   ├── users/UsersTest.java        # Users API tests (10)
│       │   └── auth/AuthTest.java          # Auth API tests (6)
│       └── resources/
│           └── allure.properties           # Allure configuration
├── Dockerfile                              # Docker container setup
├── Jenkinsfile                             # CI/CD pipeline definition
├── pom.xml                                 # Maven dependencies
├── testng.xml                              # TestNG suite configuration
└── README.md
```

## Running Tests Locally

### Using Maven
```bash
mvn clean test
```

### View Allure Report
```bash
mvn allure:serve
```

### Using Docker
```bash
docker build -t api-tests .
docker run api-tests
```

## Jenkins Setup

### 1. Install Jenkins

**Option A: Docker (Recommended)**
```bash
docker run -d -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home --name jenkins jenkins/jenkins:lts
```

**Option B: Local Installation**
Download from: https://www.jenkins.io/download/

### 2. Initial Jenkins Configuration

1. Access Jenkins at `http://localhost:8080`
2. Get initial admin password:
   ```bash
   docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
   ```
3. Install suggested plugins

### 3. Install Required Plugins

Navigate to: **Manage Jenkins → Plugins → Available Plugins**

Install:
- Git Plugin
- Pipeline Plugin
- Allure Plugin
- HTML Publisher Plugin
- TestNG Results Plugin
- Email Extension Plugin

### 4. Configure Tools

**Manage Jenkins → Tools**

**Maven Configuration:**
- Name: `Maven`
- Version: 3.9.14 (install automatically)

**Allure Configuration:**
- Name: `Allure`
- Version: 2.38.1 (install automatically)

### 5. Create Pipeline Job

1. **New Item → Pipeline**
2. **Name**: `FakeStore-API-Tests`
3. **Pipeline Configuration**:
   - Definition: `Pipeline script from SCM`
   - SCM: `Git`
   - Repository URL: Your GitHub repository URL
   - Branch: `*/main`
   - Script Path: `Jenkinsfile`
4. **Build Triggers**:
   - Enable: `GitHub hook trigger for GITScm polling`

### 6. Configure Email Notifications

**Manage Jenkins → System → Extended E-mail Notification**

1. **SMTP Configuration**:
   - SMTP server: `smtp.gmail.com`
   - SMTP Port: `587`
   - Use SSL: ✓
   - Use TLS: ✓

2. **Add Gmail Credentials**:
   - Manage Jenkins → Credentials → System → Global credentials
   - Add Credentials:
     - Kind: Username with password
     - Username: Your Gmail address
     - Password: Gmail App Password (not regular password)
     - ID: `gmail-credentials`

3. **Get Gmail App Password**:
   - Go to Google Account → Security
   - Enable 2-Step Verification
   - Generate App Password for "Mail"
   - Use this password in Jenkins

### 7. Configure GitHub Webhook

**For Local Jenkins (using ngrok):**

1. **Install ngrok**: Download from https://ngrok.com/

2. **Expose Jenkins**:
   ```bash
   ngrok http 8080
   ```
   Copy the HTTPS URL (e.g., `https://abc123.ngrok-free.app`)

3. **In GitHub Repository**:
   - Go to **Settings → Webhooks → Add webhook**
   - Payload URL: `https://your-ngrok-url/github-webhook/`
   - Content type: `application/json`
   - Events: `Just the push event`
   - Active: ✓

4. **Verify Webhook**:
   - After creating webhook, GitHub shows delivery status
   - Click on webhook → Recent Deliveries tab
   - You should see successful deliveries (green checkmark)
   - Make a test commit to trigger the webhook

**For Production Jenkins:**
- Use your actual Jenkins URL: `http://your-jenkins-server:8080/github-webhook/`

### 8. Test Webhook Integration

1. **Make a code change**:
   ```bash
   git add .
   git commit -m "Test webhook trigger"
   git push origin main
   ```

2. **Verify in Jenkins**:
   - Go to your Jenkins job
   - You should see a new build triggered automatically
   - Build should show "Started by GitHub push by [username]"

3. **Check GitHub Webhook Status**:
   - GitHub → Repository → Settings → Webhooks
   - Click on your webhook
   - Check "Recent Deliveries" tab
   - Green checkmark = successful delivery
   - Red X = failed delivery (check response for errors)

## Test Reports

After pipeline execution, reports are available at:

- **Allure Report**: `http://localhost:8080/job/FakeStore-API-Tests/[build-number]/allure/`
- **TestNG Report**: `http://localhost:8080/job/FakeStore-API-Tests/TestNG_Report/`
- **JUnit Results**: Available in Jenkins build page
- **Console Output**: `http://localhost:8080/job/FakeStore-API-Tests/[build-number]/console`

## API Test Coverage

### Products API (12 tests)
- ✓ Get all products
- ✓ Get single product
- ✓ Get categories
- ✓ Get products by category
- ✓ Create product
- ✓ Update product
- ✓ Delete product
- ✓ Get invalid product
- ✓ Get limited products (query param)
- ✓ Get sorted products (query param)
- ✓ Validate product response schema
- ✓ Create product with invalid data

### Carts API (9 tests)
- ✓ Get all carts
- ✓ Get single cart
- ✓ Create cart
- ✓ Update cart
- ✓ Delete cart
- ✓ Get user carts
- ✓ Get carts with date range
- ✓ Validate cart response schema
- ✓ Create cart with invalid data

### Users API (10 tests)
- ✓ Get all users
- ✓ Get single user
- ✓ Create user
- ✓ Update user
- ✓ Delete user
- ✓ Get limited users
- ✓ Get sorted users
- ✓ Validate user response schema
- ✓ Create user with invalid data
- ✓ Get invalid user

### Auth API (6 tests)
- ✓ Successful login
- ✓ Invalid credentials
- ✓ Missing username
- ✓ Missing password
- ✓ Empty body
- ✓ Validate login response schema

**Total: 37 comprehensive test cases**

## CI/CD Pipeline Stages

1. **Checkout**: Pull latest code from Git repository
2. **Build**: Compile project with Maven
3. **Test**: Execute REST Assured test suite (37 tests)
4. **Generate Allure Report**: Create detailed test execution report
5. **Publish Reports**: Publish Allure and TestNG reports
6. **Notifications**: Send email alerts on success/failure

## Notifications

### Email Notifications
- **Success**: Professional HTML email with test statistics and report links
- **Failure**: Alert email with console output link
- **Recipient**: Configured in Jenkinsfile

### Slack Notifications (Optional)
- Rich formatted messages with build status
- Interactive buttons for quick access to reports
- Configured via Slack webhook URL

## Troubleshooting

### Tests fail in Jenkins but pass locally
- Check Java/Maven versions match
- Verify network connectivity to fakestoreapi.com
- Check Jenkins console output for detailed errors

### Webhook not triggering
- Ensure Jenkins is accessible from internet (use ngrok for local)
- Verify webhook URL is correct in GitHub settings
- Check GitHub webhook delivery status (Recent Deliveries tab)
- Ensure "GitHub hook trigger for GITScm polling" is enabled in Jenkins job

### Email notifications not working
- Verify Gmail App Password (not regular password)
- Check SMTP settings in Jenkins
- Test connection in Extended E-mail Notification settings
- Check Jenkins console for email sending errors

### Reports not publishing
- Check Allure plugin is installed
- Verify report paths in Jenkinsfile
- Ensure tests are generating allure-results

### Allure report not generating
- Verify Allure tool is configured in Jenkins
- Check allure-results directory exists in target/
- Ensure allure.properties is in src/test/resources/

## Project Features

✅ **Comprehensive Test Suite**: 37 tests covering all FakeStore API endpoints
✅ **Organized Structure**: Tests grouped by domain (Products, Carts, Users, Auth)
✅ **Centralized Test Data**: Reusable test data in TestData class
✅ **Base Test Class**: Shared configuration and setup
✅ **Allure Reporting**: Detailed test execution reports with request/response logging
✅ **TestNG Integration**: Parallel execution and test grouping
✅ **CI/CD Pipeline**: Automated testing on every commit
✅ **GitHub Webhook**: Automatic build triggers
✅ **Email Notifications**: Professional HTML emails with test results
✅ **Docker Support**: Containerized test execution
✅ **Schema Validation**: JSON schema validation for API responses
✅ **Error Handling**: Comprehensive negative test scenarios

## Repository

GitHub: https://github.com/Kalisha1234/Automation-Test-Execution-with-Jenkins-CI-CD-Completion-requirements

## License

MIT License
