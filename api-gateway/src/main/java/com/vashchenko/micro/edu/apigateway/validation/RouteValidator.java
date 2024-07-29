package com.vashchenko.micro.edu.apigateway.validation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

@Component
@ConfigurationProperties(prefix = "spring.cloud.gateway.unsecured")
public class RouteValidator {
    private List<String> routes;

    public Predicate<ServerHttpRequest> isSecured =
            request -> routes
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public List<String> getUnsecuredUrls() {
        return routes;
    }

    public void setUnsecuredUrls(List<String> unsecuredUrls) {
        routes = unsecuredUrls;
    }
}
