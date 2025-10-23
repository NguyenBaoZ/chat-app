package com.chatapp.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final JwtAuthGatewayFilter jwtFilter = new JwtAuthGatewayFilter();

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/users/**")
                        .filters(f -> f
                                .rewritePath("/api/users/(?<segment>.*)", "/${segment}")
                                .filter(jwtFilter))
                        .uri("lb://user-service"))

                // .route("message-service", r -> r.path("/api/messages/**")
                //         .filters(f -> f.stripPrefix(2)
                //                        .filter(jwtFilter))
                //         .uri("lb://message-service"))

                // .route("group-service", r -> r.path("/api/groups/**")
                //         .filters(f -> f.stripPrefix(2)
                //                        .filter(jwtFilter))
                //         .uri("lb://group-service"))

                .route("auth-service-no-jwt", r -> r.path("/api/auth/login", "/api/auth/register")
                        .filters(f -> f
                                .rewritePath("/api/auth/(?<segment>.*)", "/${segment}"))
                        .uri("lb://auth-service"))

                .route("auth-service-jwt", r -> r.path("/api/auth/**")
                        .and().not(p -> p.path("/api/auth/login", "/api/auth/register"))
                        .filters(f -> f
                                .rewritePath("/api/auth/(?<segment>.*)", "/${segment}")
                                .filter(jwtFilter))
                        .uri("lb://auth-service"))

                // .route("file-service", r -> r.path("/api/files/**")
                //         .filters(f -> f.stripPrefix(2)
                //                        .filter(jwtFilter))
                //         .uri("lb://file-service"))

                // .route("notification-service", r -> r.path("/api/notifications/**")
                //         .filters(f -> f.stripPrefix(2)
                //                        .filter(jwtFilter))
                //         .uri("lb://notification-service"))

                // .route("statistic-service", r -> r.path("/api/statistics/**")
                //         .filters(f -> f.stripPrefix(2)
                //                        .filter(jwtFilter))
                //         .uri("lb://statistic-service"))
                .build();
    }
}
