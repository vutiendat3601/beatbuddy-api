package vn.io.vutiendat3601.beatbuddy.api.application.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import vn.io.vutiendat3601.beatbuddy.api.application.security.RouteSpecification;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Configuration
@ConfigurationProperties(prefix = "security")
public class RouteSpecificationConfig {
  @JsonProperty("route-specs")
  private List<RouteSpecification> routeSpecs = new LinkedList<>();

  private final String[] GET_AUTHENTICATED_ROUTES = {
    "/v1/auth/me", "/v1/me/playlists", "/v1/me/like", "/v1/auth/resources"
  };
  private final String[] POST_AUTHENTICATED_ROUTES = {
    "/v1/auth/resources",
  };
  private final String[] GET_PUBLIC_ROUTES = {
    "/v1/auth/token/client-token", "/apidocs*/**", "/swagger-ui/**"
  };

  public Customizer<
          AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>
      getRouteSpecification() {
    return req -> {
      for (RouteSpecification routeSpec : routeSpecs) {
        req.requestMatchers(routeSpec.getMethod(), routeSpec.getUris().toArray(String[]::new))
            .hasAnyAuthority(routeSpec.getAuthorities().toArray(String[]::new));
      }
      // Authenticated routes
      req.requestMatchers(GET, GET_AUTHENTICATED_ROUTES).authenticated();
      req.requestMatchers(POST, POST_AUTHENTICATED_ROUTES).authenticated();

      // Public routes
      req.requestMatchers(GET, GET_PUBLIC_ROUTES).permitAll();

      // Deny all other requests
      req.anyRequest().denyAll();
    };
  }
}
