# Allure Reporting Setup

## What is Allure?
Allure is a flexible, lightweight test report tool that shows detailed test execution results with beautiful visualizations, screenshots, and step-by-step test flows.

## Local Setup

### 1. Install Allure Command Line

**Windows (using Scoop):**
```bash
scoop install allure
```

**Or download manually:**
- Download from: https://github.com/allure-framework/allure2/releases
- Extract to `C:\allure`
- Add to PATH: `C:\allure\bin`

### 2. Run Tests and Generate Report

```bash
# Run tests
mvn clean test

# Generate Allure report
mvn allure:report

# Open report in browser
mvn allure:serve
```

## Jenkins Setup

### 1. Install Allure Plugin

1. Go to Jenkins → Manage Jenkins → Plugins
2. Search for "Allure"
3. Install "Allure Jenkins Plugin"
4. Restart Jenkins

### 2. Configure Allure in Jenkins

1. Go to Manage Jenkins → Tools
2. Scroll to "Allure Commandline"
3. Click "Add Allure Commandline"
4. Name: `Allure`
5. Check "Install automatically"
6. Version: Select latest (e.g., 2.24.0)
7. Click "Save"

### 3. View Reports

After pipeline runs:
1. Go to your job
2. Click on build number (e.g., #1)
3. Click "Allure Report" in left sidebar
4. Explore beautiful test reports!

## Report Features

✅ Test execution timeline
✅ Test case details with steps
✅ Request/Response logs (REST Assured)
✅ Failure screenshots
✅ Test categories and suites
✅ Trend graphs
✅ Historical data

## Commands

```bash
# Run tests
mvn clean test

# Generate report (HTML)
mvn allure:report

# Serve report (opens browser)
mvn allure:serve

# Clean old results
mvn clean
```

## Report Location

- **Results**: `target/allure-results/`
- **Report**: `target/site/allure-maven-plugin/`

## Troubleshooting

**Issue: Allure command not found**
```bash
# Check installation
allure --version

# If not found, add to PATH or reinstall
```

**Issue: No test results**
- Ensure tests ran successfully
- Check `target/allure-results/` exists
- Verify pom.xml has Allure dependencies

**Issue: Report not showing in Jenkins**
- Install Allure Jenkins Plugin
- Configure Allure Commandline in Tools
- Check Jenkinsfile has allure step
