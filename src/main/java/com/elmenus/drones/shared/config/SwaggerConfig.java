package com.elmenus.drones.shared.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Drone Management API")
                        .description("API documentation for Drone Management System")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Drone Management Wiki"));
    }
}