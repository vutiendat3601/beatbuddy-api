package vn.io.vutiendat3601.beatbuddy.api.application.config;

import org.keycloak.authorization.client.AuthzClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import vn.io.vutiendat3601.beatbuddy.api.application.filter.DecodedJwtContextFilter;
import vn.io.vutiendat3601.beatbuddy.api.application.filter.KeycloakExchangeAuthorizationTokenFilter;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak.ResourceManagementConfig;

@Configuration
public class SecurityConfig {
  private final RouteSpecificationConfig routeAuthzSpecConfig;
  private final CorsConfig corsConfig;
  private final JwtAuthenticationConverter jwtAuthenticationConverter;
  private final AuthzClient authzClient;

  public SecurityConfig(
      RouteSpecificationConfig routeAuthzSpecConfig,
      CorsConfig corsConfig,
      JwtAuthenticationConverter jwtAuthenticationConverter,
      ResourceManagementConfig resourceManagementConfig) {
    this.routeAuthzSpecConfig = routeAuthzSpecConfig;
    this.corsConfig = corsConfig;
    this.jwtAuthenticationConverter = jwtAuthenticationConverter;
    authzClient = resourceManagementConfig.createClient();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(routeAuthzSpecConfig.getRouteSpecification())
        .oauth2ResourceServer(
            oauth -> oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
        .cors(cors -> cors.configurationSource(corsConfig))
        .csrf(csrf -> csrf.disable())
        .addFilterBefore(
            new KeycloakExchangeAuthorizationTokenFilter(authzClient),
            BearerTokenAuthenticationFilter.class)
        .addFilterAfter(new DecodedJwtContextFilter(), AuthorizationFilter.class)
        .build();
  }
}
