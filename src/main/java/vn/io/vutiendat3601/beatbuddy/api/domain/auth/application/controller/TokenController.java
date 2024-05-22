package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Token")
@RequestMapping("v1/auth/tokens")
@RestController
public class TokenController {
  // private final AuthzClient webConfidentialClient;

  // public TokenController(
  //     @Qualifier("keycloakWebConfidentialClient") AuthzClient keycloakWebConfidentialClient) {
  //   this.webConfidentialClient = keycloakWebConfidentialClient;
  // }

  // @Operation(summary = "Get Public Client Token")
  // @GetMapping("tokens/client-token")
  // public ResponseEntity<TokenDto> getClientToken() {
  //   final AccessTokenResponse accToken = webConfidentialClient.obtainAccessToken();
  //   return ResponseEntity.ok(
  //       new TokenDto(accToken.getToken(), accToken.getTokenType(), accToken.getExpiresIn()));
  // }
}
