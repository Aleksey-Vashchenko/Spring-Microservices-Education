package com.vashchenko.micro.edu.apigateway.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vashchenko.micro.edu.apigateway.service.IncomeJwtVerifier;
import com.vashchenko.micro.edu.apigateway.service.OutcomeJwtProvider;
import com.vashchenko.micro.edu.apigateway.validation.RouteValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component

public class JwtAuthenticationFilter implements GatewayFilter{
    private final RouteValidator routeValidator;
    private final IncomeJwtVerifier incomeJwtVerifier;
    private final OutcomeJwtProvider outcomeJwtProvider;
    private final ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(RouteValidator routeValidator,
                                   IncomeJwtVerifier incomeJwtVerifier,
                                   OutcomeJwtProvider outcomeJwtProvider,
                                   ObjectMapper objectMapper) {
        this.routeValidator = routeValidator;
        this.incomeJwtVerifier = incomeJwtVerifier;
        this.outcomeJwtProvider = outcomeJwtProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routeValidator.isSecured.test(request)) {
            if (isAuthMissing(request)) {
                this.onError(exchange,"Authorization header is missing in request");
            }

            String authHeader = getAuthHeader(request);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
                Claims claims = null;
                try {
                    claims = incomeJwtVerifier.getValidatedClaims(authHeader);
                    String newToken = outcomeJwtProvider.generateToken(claims);
                    exchange.getResponse().getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + newToken);
                }
                catch (MalformedJwtException e) {
                    logRequest(request,claims);
                    this.onError(exchange,e.getMessage());
                }
                catch (Exception e) {
                    this.onError(exchange,e.getMessage());
                }
            }
        }
        return chain.filter(exchange);
    }

    public Mono<Void> onError(ServerWebExchange exchange, String err) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        exchange.getResponse().setStatusCode(httpStatus);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ErrorResponse errorResponse = new ErrorResponse(err);
        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(errorResponse);
        } catch (JsonProcessingException e) {
            bytes = new byte[0];
        }

        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory()
                .wrap(bytes)));
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void logRequest(ServerHttpRequest request, Claims claims) {
        String url = request.getURI().toString();
        String method = request.getMethod().name();
        String userId = claims.get("id") != null ? claims.get("id").toString() : null;
        String roles = claims.get("roles") != null ? claims.get("roles").toString() : null;
        String name = claims.get("name") != null ? claims.get("name").toString() : null;
        String issuer = claims.getIssuer() != null ? claims.getIssuer() : null;
    }

    private static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }


}
