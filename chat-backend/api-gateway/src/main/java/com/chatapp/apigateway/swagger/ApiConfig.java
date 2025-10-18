package com.chatapp.apigateway.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
                        .version("1.0.0"))
                .servers(List.of(
                        // Gateway
                        new Server().url("http://localhost:8080").description("🚪 API Gateway"),

                        // Auth & User Management
                        // new Server().url("http://localhost:8081").description("🔐 Auth Service"),
                        new Server().url("http://localhost:8082").description("👤 User Service")

                        // Chat & Group
                        // new Server().url("http://localhost:8083").description("💬 Message Service"),
                        // new Server().url("http://localhost:8084").description("👥 Group Service"),
                        // new Server().url("http://localhost:8085").description("📁 File Service"),

                        // Realtime & Notification
                        // new Server().url("http://localhost:8086").description("🔔 Notification Service"),
                        // new Server().url("http://localhost:8087").description("⚡ Realtime Gateway"),

                        // Analytics
                        // new Server().url("http://localhost:8088").description("📊 Statistic Service"),

                        // Discovery
                        // new Server().url("http://localhost:8761").description("🧭 Discovery Service (Eureka)")
                ));
    }
}
