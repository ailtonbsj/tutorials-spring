# JPA on OracleDB and HikariCP

Using JPA with a unique database and setting conections pool.

- Spring Boot
- Oracle Database 11g
- Hiraki Connection Pool

## Commands

```bash
# Start instance of Oracle Database 11g
docker compose up -d

# Use SQL PLus or DBeaver to generate
# schema and database user from schema.sql
```

## Swagger UI

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Check amount and name of connections pool

```sql
-- See amount, program name and module name of CP
SELECT USERNAME, PROGRAM, MODULE FROM V$SESSION WHERE USERNAME = 'STUDYUSER';
```