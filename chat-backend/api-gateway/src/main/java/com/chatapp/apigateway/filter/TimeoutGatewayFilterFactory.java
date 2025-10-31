package com.chatapp.apigateway.filter;

import java.time.Duration;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TimeoutGatewayFilterFactory 
    extends AbstractGatewayFilterFactory<TimeoutGatewayFilterFactory.Config> {

    public TimeoutGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> 
            chain.filter(exchange)
                 .timeout(Duration.ofSeconds(config.getTimeoutSeconds()))
                 .onErrorResume(TimeoutException.class, e -> {
                     exchange.getResponse().setStatusCode(HttpStatus.GATEWAY_TIMEOUT);
                     return exchange.getResponse().setComplete();
                 });
    }

    public static class Config {
        private int timeoutSeconds = 3;

        // getters and setters
        public int getTimeoutSeconds() {
            return timeoutSeconds;
        }

        public void setTimeoutSeconds(int timeoutSeconds) {
            this.timeoutSeconds = timeoutSeconds;
        }
    }
}
