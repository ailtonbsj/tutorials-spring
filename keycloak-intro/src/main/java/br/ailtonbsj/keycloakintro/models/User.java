package br.ailtonbsj.keycloakintro.models;

public record User(
        String password,
        String clientId,
        String grantType,
        String username) {
}
