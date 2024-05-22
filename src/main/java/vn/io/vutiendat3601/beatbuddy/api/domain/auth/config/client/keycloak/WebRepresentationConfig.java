package vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak;

import java.util.Map;

import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "auth.client.keycloak.web-representation")
public class WebRepresentationConfig {
  @JsonProperty("auth-server-url")
  private String authServerUrl;

  @JsonProperty("realm")
  private String realm;

  @JsonProperty("client-id")
  private String clientId;

  @JsonProperty("client-secret")
  private String clientSecret;

  public AuthzClient createClient() {
    final Configuration config =
        new Configuration(authServerUrl, realm, clientId, Map.of("secret", clientSecret), null);
    return AuthzClient.create(config);
  }
}
