package vn.io.vutiendat3601.beatbuddy.api.application.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.application.security.RouteSpecification;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Configuration
@ConfigurationProperties(prefix = "security")
public class RouteSpecificationConfig {
  @JsonProperty("route-specs")
  private List<RouteSpecification> routeSpecs = new LinkedList<>();

  public Customizer<
          AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>
      getRouteSpecification() {
    return req -> {
      for (RouteSpecification routeSpec : routeSpecs) {
        req.requestMatchers(routeSpec.getMethod(), routeSpec.getUris().toArray(String[]::new))
            .hasAnyAuthority(routeSpec.getAuthorities().toArray(String[]::new));
      }
      // Authenticated routes
      req.requestMatchers(GET, "/v1/auth/users/me").authenticated();
      req.requestMatchers(POST, "/v1/auth/resources").authenticated();

      // Public routes
      req.requestMatchers(GET, "/v1/auth/token/client-token").permitAll();
      req.requestMatchers(GET, "/apidocs*/**", "/swagger-ui/**").permitAll();
      req.anyRequest().denyAll();
    };
  }
}
