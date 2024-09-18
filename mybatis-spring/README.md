# Using MyBatis with Spring Boot

### History of commands

```bash
# Install SDKMAN
curl -s "https://get.sdkman.io" | bash

# Install JDK
sdk install java

# Install maven
sdk install maven

# Start containter with MySQL server on 3306
docker compose up -d

# Create mybatis_spring database accessing:
# http://localhost:8081 with credentials root:password
```

### MySQL database

```sql
create database if not exists mybatis_spring;
use mybatis_spring;

create table if not exists users (
    id integer primary key auto_increment,
    name varchar(100),
    salary double
);

insert into users values
    (null, 'John', 4500.00),
    (null, 'Mary', 5000.00);
```