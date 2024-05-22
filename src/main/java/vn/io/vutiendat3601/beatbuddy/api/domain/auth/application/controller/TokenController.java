package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.controller;

import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.TokenDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak.WebRepresentationConfig;

@Tag(name = "Token")
@RequestMapping("v1/auth/token")
@RestController
public class TokenController {
  private final AuthzClient webRepresentationClient;

  public TokenController(WebRepresentationConfig webRepresentationConfig) {
    webRepresentationClient = webRepresentationConfig.createClient();
  }

  @Operation(summary = "Get Public Client Token")
  @GetMapping("client-token")
  public ResponseEntity<TokenDto> getClientToken() {
    final AccessTokenResponse accToken = webRepresentationClient.obtainAccessToken();
    return ResponseEntity.ok(
        new TokenDto(accToken.getToken(), accToken.getTokenType(), accToken.getExpiresIn()));
  }
}
