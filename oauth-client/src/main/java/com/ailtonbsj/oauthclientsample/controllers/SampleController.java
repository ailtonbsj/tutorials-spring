package com.ailtonbsj.oauthclientsample.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SampleController {

    final String TEMPLATE = """
        <h3>User info:</h3>
        <pre style="white-space: pre-wrap; word-wrap: break-word;">
        Principal: %s,
        
        Email attribute: %s,
        
        Authorities: %s,
        
        JWT: %s
        </pre>
        <h1>You need to be authenticated to see this route!</h1>""";

    @GetMapping("/public")
    public String publicRoute() {
        return "<h1>Everyone can access public routes!</h1>";
    }

    @GetMapping("/private-with-cookie")
    public String privateWithCookie(@AuthenticationPrincipal OidcUser principal) {
        return String.format(
            TEMPLATE,
            principal,
            principal.getAttribute("email"),
            principal.getAuthorities(),
            principal.getIdToken().getTokenValue()
        );
    }

    @GetMapping("/private-with-jwt")
    public String privateWithJWT(@AuthenticationPrincipal Jwt jwt) {
        return String.format(
            TEMPLATE,
            jwt.getClaims(),
            jwt.getClaim("email"),
            jwt.getClaim("name"),
            jwt.getTokenValue()
        );
    }

}
