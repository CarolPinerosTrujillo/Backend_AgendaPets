package com.agendapets.agendapets.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class OpenApiConfig {

    public static final String SECURITY_SCHEME = "bearerAuth";

    @Bean
    public OpenAPI agendapetsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AgendaPets API")
                        .description("""
                                API REST de reservas para pet grooming.

                                - Autenticación: JWT (POST /api/auth/login)
                                - Endpoints públicos: health, servicios, ocupadas, login, registro
                                - Endpoints protegidos: requieren header `Authorization: Bearer <token>`
                                - Roles: ADMIN y CLIENTE
                                """)
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME))
                .components(new Components()
                        .securitySchemes(Map.of(SECURITY_SCHEME, new SecurityScheme()
                                .name(SECURITY_SCHEME)
                                .description("Pega el token JWT que devuelve el login")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))));
    }
}
