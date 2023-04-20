# History of commands

```bash
# Install Spring Boot CLI with SDKMAN!
sdk install springboot

# Project created
spring init --build=maven --java-version=17 --dependencies=web --groupId=br.ailtonbsj.deploytom --packaging=war deploy-spring-to-tomcat

# Run project
./mvnw spring-boot:run

# Navigate to project
curl http://localhost:8080/springtom/

# Build a war file on target folder
./mvnw clean package

# Download Tomcat 10 from
# https://tomcat.apache.org/download-10.cgi
# Extract package
tar -zxf apache-tomcat-10.1.7.tar.gz -C ~/.apps

# Run tomcat on terminal
./bin/catalina.sh run

# Copy .war file to webapps folder
# Wait to auto deploy

# Navigate to project
curl http://localhost:8080/deploy-sprsing-to-tomcat/

```