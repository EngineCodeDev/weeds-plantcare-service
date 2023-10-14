# Weeds Plantcare Service

Plants management application. More info coming soon!


## Prerequisites

- Docker
- JDK 17, make sure that the JAVA_HOME environment variable is properly configured


## Run the app
### 1. Docker
From the project directory run the following command:
```shell
docker-compose -f database/docker-compose.yml up -d
```
⏳ The Docker image is downloaded during first build thus it  may take a little longer than usual

### 2. Database schema
Log in to `weeds_db` as `weeds_admin` and run the following queries
```
CREATE SCHEMA app;
GRANT USAGE ON SCHEMA app TO weeds_user;
```

### 3. Liquibase tables
From the project directory run the following command to create the tables using Liquibase:
```
./gradlew :database:clean :database:build update -Pdb.dbRegion.active=local
```

### 4. Run the application
```
./gradlew bootRun -Pdb.dbRegion.active=local
```


<!-- Describe here how to start the application when new requirements appear -->
🚧 Work in progress! Be patient, cool things are coming! 🚧