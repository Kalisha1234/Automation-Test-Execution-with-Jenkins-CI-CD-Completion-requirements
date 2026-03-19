# Automation Test Execution with Jenkins CI/CD

This project demonstrates automated API testing using REST Assured with Jenkins CI/CD pipeline integration for the FakeStore API.

## Project Overview

- **API Under Test**: https://fakestoreapi.com/
- **Testing Framework**: REST Assured + TestNG
- **Build Tool**: Maven
- **CI/CD**: Jenkins Pipeline
- **Containerization**: Docker

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
│       └── java/
│           └── org/
│               └── example/
│                   ├── base/
│                   │   └── BaseTest.java
│                   ├── testdata/
│                   │   └── TestData.java
│                   ├── products/
│                   │   └── ProductsTest.java
│                   ├── carts/
│                   │   └── CartsTest.java
│                   ├── users/
│                   │   └── UsersTest.java
│                   └── auth/
│                       └── AuthTest.java
├── Dockerfile
├── Jenkinsfile
├── pom.xml
├── testng.xml
└── README.md
```

## Running Tests Locally

### Using Maven
```bash
mvn clean test
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
- HTML Publisher Plugin
- TestNG Results Plugin
- Email Extension Plugin
- Slack Notification Plugin (optional)

### 4. Configure Tools

**Manage Jenkins → Tools**

**JDK Configuration:**
- Name: `JDK11`
- JAVA_HOME: Path to Java 11 installation

**Maven Configuration:**
- Name: `Maven`
- Version: 3.9.5 (or install automatically)

### 5. Create Pipeline Job

1. **New Item → Pipeline**
2. **Name**: `FakeStore-API-Tests`
3. **Pipeline Configuration**:
   - Definition: `Pipeline script from SCM`
   - SCM: `Git`
   - Repository URL: `https://github.com/<YOUR_USERNAME>/<YOUR_REPO>.git`
   - Branch: `*/main`
   - Script Path: `Jenkinsfile`

### 6. Configure GitHub Webhook

**In GitHub Repository:**
1. Go to **Settings → Webhooks → Add webhook**
2. Payload URL: `http://<JENKINS_URL>:8080/github-webhook/`
3. Content type: `application/json`
4. Events: `Just the push event`
5. Active: ✓

**In Jenkins Job:**
1. **Build Triggers**
2. Enable: `GitHub hook trigger for GITScm polling`

### 7. Configure Notifications

**Email Notifications:**
1. **Manage Jenkins → System**
2. **Extended E-mail Notification**:
   - SMTP server: `smtp.gmail.com`
   - Port: `587`
   - Credentials: Add Gmail credentials
3. Update Jenkinsfile with your email

**Slack Notifications (Optional):**
1. Install Slack app in workspace
2. Get Slack token
3. **Manage Jenkins → System → Slack**:
   - Workspace: Your workspace
   - Credential: Add token
4. Update Jenkinsfile with channel name

## Test Reports

After pipeline execution:
- **JUnit Reports**: Available in Jenkins build page
- **HTML Reports**: Published via HTML Publisher plugin
- **TestNG Reports**: In `target/surefire-reports/`

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

## Troubleshooting

**Issue: Tests fail in Jenkins but pass locally**
- Check Java/Maven versions match
- Verify network connectivity to fakestoreapi.com

**Issue: Webhook not triggering**
- Ensure Jenkins is accessible from internet (use ngrok for local)
- Verify webhook URL is correct

**Issue: Reports not publishing**
- Check HTML Publisher plugin is installed
- Verify report paths in Jenkinsfile

## CI/CD Pipeline Stages

1. **Checkout**: Pull latest code from Git
2. **Build**: Compile project with Maven
3. **Test**: Execute REST Assured tests
4. **Generate Reports**: Publish HTML and JUnit reports
5. **Notifications**: Send email/Slack alerts

## Contributing

1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Create Pull Request

## License

MIT License
