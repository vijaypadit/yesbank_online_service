package com.yesbank.getwayserver;


import com.yesbank.getwayserver.security.AuthFilter;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication

public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
    @Autowired
    private AuthFilter authFilter;
    @Bean
    public RouteLocator yesBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()

                .route(p -> p
                        .path("/yesbank/users/**")
                .filters(f -> f
                        .filter(authFilter.apply(new AuthFilter.Config()))
                        .rewritePath("/yesbank/users/(?<segment>.*)", "/${segment}")
                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        .circuitBreaker(config -> config.setName("accountsCircuitBreaker")
                                .setFallbackUri("forward:/contactSupport")))

                .uri("lb://USERS"))
                .route(p -> p
                        .path("/yesbank/cards/**")
                .filters(f -> f
                        .filter(authFilter.apply(new AuthFilter.Config()))
                        .rewritePath("/yesbank/cards/(?<segment>.*)", "/${segment}")
                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        .retry(retryConfig -> retryConfig.setRetries(3).setMethods(HttpMethod.GET)
                                .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)))

                .uri("lb://CARDS"))
                .route(p -> p
                        .path("/yesbank/banks/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthFilter.Config()))
                                .rewritePath("/yesbank/banks/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time",
                                        LocalDateTime.now().toString()))
                        .uri("lb://BANKS"))
                .route(p -> p
                        .path("/yesbank/bankaccounts/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthFilter.Config()))
                                .rewritePath("/yesbank/bankaccounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://BANKACCOUNTS"))
                .route(p->p
                        .path("/yesbank/authuser/**")
                        .filters(f->f
                                .rewritePath("/yesbank/authuser/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time",LocalDateTime.now().toString()))
                        .uri("lb://AUTHUSER"))

                .build();
    }


    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig
                        .custom()
                        .failureRateThreshold(70)
                        .slidingWindowSize(20)
                        .waitDurationInOpenState(Duration
                                .ofSeconds(30))
                        .build())
                .timeLimiterConfig(TimeLimiterConfig
                        .custom()
                        .timeoutDuration(Duration
                                .ofSeconds(10))
                        .build())
                .build());
    }

}
