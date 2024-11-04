package br.ailtonbsj.keycloakintro.controllers;

import java.util.Arrays;
import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.ailtonbsj.keycloakintro.models.User;

@RestController
@RequestMapping("/testing")
public class TestController {

    @Autowired
    Keycloak keycloak;

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

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filter-users")
    public List<UserRepresentation> filterUsers(@RequestParam String query, @RequestParam Integer first, @RequestParam Integer max) {
        return keycloak.realm("realmtest")
                .users().search(query, first, max);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list-users-by-realm-role")
    public List<UserRepresentation> listUsersByRealmRole() {
        return keycloak.realm("realmtest")
                .roles().get("USER").getUserMembers();
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list-users-by-client-role")
    public List<UserRepresentation> listUsersByClientRole() {

        String clientUuid = keycloak.realm("realmtest").clients()
            .findByClientId("apptest").getFirst().getId();

        return keycloak.realm("realmtest").clients().get(clientUuid)
            .roles().get("ROLE_FROM_APP").getUserMembers();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-realm-role-to-user")
    public UserRepresentation addRealmRoleToUser(@RequestParam String userId) {
        RoleRepresentation userRole = keycloak.realm("realmtest")
            .roles().get("USER").toRepresentation();
        keycloak.realm("realmtest").users().get(userId)
            .roles().realmLevel().add(Arrays.asList(userRole));
        return keycloak.realm("realmtest").users().get(userId).toRepresentation();
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-client-role-to-user")
    public UserRepresentation addClientRoleToUser(@RequestParam String userId) {

        String clientUuid = keycloak.realm("realmtest").clients()
            .findByClientId("apptest").getFirst().getId();

        RoleRepresentation role = keycloak.realm("realmtest").clients().get(clientUuid)
            .roles().get("ROLE_FROM_APP").toRepresentation();
        
        keycloak.realm("realmtest").users().get(userId)
            .roles().clientLevel(clientUuid).add(Arrays.asList(role));

        return keycloak.realm("realmtest").users().get(userId).toRepresentation();

    }

}
