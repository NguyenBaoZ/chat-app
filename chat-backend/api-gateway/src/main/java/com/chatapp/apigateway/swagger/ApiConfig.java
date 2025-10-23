package com.chatapp.apigateway.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public OpenAPI apiGatewayOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Chat App - API Gateway")
                        .description("""
                                Centralized API Gateway for Chat Application.
                                <br><br>
                                Use this gateway to route requests to all microservices below.
                                <br>
                                """)
                        .version("1.0.0"));
    }
}
