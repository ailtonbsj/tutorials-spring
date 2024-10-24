package br.ailtonbsj.keycloakintro.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.ailtonbsj.keycloakintro.models.User;

@RestController
@RequestMapping("/testing")
public class TestController {

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody User user) {

        final String TOKEN_URL = "http://localhost:8081/realms/realmtest/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", user.clientId());
        formData.add("username", user.username());
        formData.add("password", user.password());
        formData.add("grant_type", user.grantType());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

        RestTemplate rest = new RestTemplate();
        var response = rest.postForEntity(TOKEN_URL, entity, String.class);
        return response;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/authenticated")
    public String list() {
        return "Everyone authenticated should list!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adminers")
    public String create() {
        return "You are a Admin! Just adminers can create!";
    }

}
