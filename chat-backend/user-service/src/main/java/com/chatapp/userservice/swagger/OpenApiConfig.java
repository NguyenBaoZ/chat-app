package com.chatapp.userservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
            .info(new Info()
            .title("User Service API")
            .version("1.0")
            .description("API for User Service"));
    }
}

