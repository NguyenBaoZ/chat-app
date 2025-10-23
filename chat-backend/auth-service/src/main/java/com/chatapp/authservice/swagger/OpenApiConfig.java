package com.chatapp.authservice.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
            .info(new Info()
            .title("Auth Service API")
            .version("1.0")
            .description("API for Auth Service"))
            .servers(List.of(
                        new Server().url("http://localhost:8080/auth-service")
                                .description("Via API Gateway")
            )); 
    }
}

