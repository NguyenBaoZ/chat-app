package com.chatapp.apigateway.config;

import java.time.Duration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chatapp.apigateway.filter.JwtAuthGatewayFilter;
import com.chatapp.apigateway.filter.TimeoutGatewayFilterFactory;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    private final JwtAuthGatewayFilter jwtFilter = new JwtAuthGatewayFilter();

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder,
                          TimeoutGatewayFilterFactory timeoutFilter) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/users/**")
                .filters(f -> f
                    .rewritePath("/api/users/(?<segment>.*)", "/${segment}")
                    .filter(jwtFilter)

                    // 1. Circuit Breaker
                    .circuitBreaker(config -> config
                        .setName("user-service-cb")
                        .setFallbackUri("forward:/fallback/user"))

                    // 2. Rate Limiter (Redis)
                    .requestRateLimiter(config -> config
                        .setRateLimiter(redisRateLimiter())
                        .setKeyResolver(ipKeyResolver()))

                    // 3. Timeout
                    .filter(timeoutFilter.apply(c -> c.setTimeoutSeconds(5)))
                    )                       
                .uri("lb://user-service"))

                // .route("message-service", r -> r.path("/api/messages/**")
                //         .filters(f -> f.stripPrefix(2)
                //                        .filter(jwtFilter))
                //         .uri("lb://message-service"))

                // .route("group-service", r -> r.path("/api/groups/**")
                //         .filters(f -> f.stripPrefix(2)
                //                        .filter(jwtFilter))
                //         .uri("lb://group-service"))

                .route("auth-service", r -> r.path("/api/auth/**")
                        .filters(f -> f
                                .rewritePath("/api/auth/(?<segment>.*)", "/${segment}")
                                // 1. Circuit Breaker
                                .circuitBreaker(config -> config
                                    .setName("auth-service-cb")
                                    .setFallbackUri("forward:/fallback/auth"))

                                // 2. Rate Limiter (Redis)
                                .requestRateLimiter(config -> config
                                    .setRateLimiter(redisRateLimiter())
                                    .setKeyResolver(ipKeyResolver()))

                                // 3. Timeout
                                .filter(timeoutFilter.apply(c -> c.setTimeoutSeconds(5)))
                                )
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

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(100, 200, 1);
}
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
            exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
        );
    }
}
