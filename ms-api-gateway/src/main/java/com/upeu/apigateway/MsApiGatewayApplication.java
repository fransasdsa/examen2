package com.upeu.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
@SpringBootApplication
@EnableWebSecurity
public class MsApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsApiGatewayApplication.class, args);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF con la nueva API
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()  // Requiere autenticación para todas las solicitudes
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwkSetUri("http://localhost:8888/.well-known/jwks.json")));  // Asegúrate de tener tu URI de JWKS

        return http.build();
    }
}
