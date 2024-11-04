package br.ailtonbsj.keycloakintro.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] publicRoutes = new String[] { 
        "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
        "/testing/public", "/testing/auth" };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> 
                authorize
                // requestMatchers(publicRoutes).permitAll()
                // .anyRequest().authenticated()
                .anyRequest().permitAll()
            );
            
        http.oauth2ResourceServer(
            oauth2 -> oauth2.jwt(
                jwt -> jwt.jwtAuthenticationConverter(
                    new JWTConverter()
                )
            )
        );
        return http.build();
    }

}
