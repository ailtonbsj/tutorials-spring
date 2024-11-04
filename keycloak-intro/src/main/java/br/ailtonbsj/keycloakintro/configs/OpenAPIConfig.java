package br.ailtonbsj.keycloakintro.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {

    private static final String TOKEN_PREFIX = "bearer";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().components(
                new Components().addSecuritySchemes(
                        TOKEN_PREFIX,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(TOKEN_PREFIX)
                )
        )
        .addSecurityItem(new SecurityRequirement().addList(TOKEN_PREFIX));
    }
}
