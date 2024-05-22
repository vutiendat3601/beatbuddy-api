package vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "auth.client.keycloak.user-management")
public class UserManagementConfig {
  @JsonProperty("auth-server-url")
  private String authServerUrl;

  @JsonProperty("realm")
  private String realm;

  @JsonProperty("client-id")
  private String clientId;

  @JsonProperty("client-secret")
  private String clientSecret;

  public Keycloak createClient() {
    return KeycloakBuilder.builder()
        .serverUrl(authServerUrl)
        .realm(realm)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .grantType(CLIENT_CREDENTIALS)
        .build();
  }
}
