# Spring Boot with OAuth2 Client

```
OAuth flow: Authorization Code Flow

Resource Owner           --> Me
Client                   --> SpringBoot API
Authorization Server/IdP --> Google
Resource Server          --> SpringBoot API

URI Callback: http://localhost:8080/login/oauth2/code/google
```

## Some commands

```bash
# Test with cookie
curl http://localhost:8080/private-with-cookie --cookie "JSESSIONID=$COOKIE" -v

# Test with JWT
curl http://localhost:8080/private-with-jwt -H "Authorization: Bearer $TOKEN" -v
```

## Google Cloud (Authorization Server)

[https://console.cloud.google.com/apis/credentials](https://console.cloud.google.com/apis/credentials)
