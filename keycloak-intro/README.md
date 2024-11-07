# Spring Boot with Keycloak

```
Resource Owener          --> Me
Client                   --> Browser/Curl/Thunder Client
Authorization Server/IdP --> Keycloak server
Resource Server          --> SpringBoot API

Realm: realmtest
ClientId: apptest
OAuth flow: Authorization Code Flow (Standard flow)
```

### History of commands

```bash
# Run Keycloak Server
docker compose up -d

# Fix Account is not fully set up:
# Go to: Authentication > Required actions > Verify Profile > Off

# See Thunder Client collections or access Swagger on:
# http://localhost:8080/swagger-ui/index.html

# Get Authentication Token with a user credentials like:
{
  "clientId": "apptest",
  "username": "manager",
  "password": "123456",
  "grantType": "password"
}

# Use bearer token on others requests

### Using Service Accounts ###

# Go to realmtest > Clients > apptest > Settings
# Enable (On) Client authentication
# Enable (v) Service accounts roles
# Go to realmtest > Clients > apptest > Credentials > Client Secret
# Go to realmtest > Clients > apptest > Service accounts roles
# Click [Assign role] and added some realm-management roles
```

#### Keycloak Admin

[http://localhost:8081](http://localhost:8081)

#### OpenId Configurations

[http://localhost:8081/realms/realmtest/.well-known/openid-configuration](http://localhost:8081/realms/realmtest/.well-known/openid-configuration)