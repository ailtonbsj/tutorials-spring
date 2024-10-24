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

# See Thunder Client collections

# Fix Account is not fully set up:
# Go to: Authentication > Required actions > Verify Profile > Off
```

#### Keycloak Admin

[http://localhost:8081](http://localhost:8081)

#### OpenId Configurations

[http://localhost:8081/realms/realmtest/.well-known/openid-configuration](http://localhost:8081/realms/realmtest/.well-known/openid-configuration)