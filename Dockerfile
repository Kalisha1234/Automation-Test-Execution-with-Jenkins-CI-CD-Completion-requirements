FROM maven:3.9.5-eclipse-temurin-11

WORKDIR /app

COPY pom.xml .
COPY testng.xml .
COPY src ./src

RUN mvn clean test -DskipTests=true

CMD ["mvn", "clean", "test"]
