@echo off
echo Checking Jenkins status...
docker ps -a | findstr jenkins

echo.
echo Restarting Jenkins...
docker restart jenkins

echo.
echo Waiting for Jenkins to start (30 seconds)...
timeout /t 30 /nobreak

echo.
echo Jenkins should be ready at http://localhost:8080
echo.
pause
