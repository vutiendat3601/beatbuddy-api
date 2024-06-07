package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.TokenDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDetailDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.presenter.UserPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak.WebRepresentationConfig;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.AuthUser;

@Tag(name = "Auth")
@RequestMapping("v1/auth")
@RestController
public class AuthController {
  private final AuthzClient webRepresentationClient;
  private final UserPresenter userPresenter;
  private final AuthUser userResource;

  public AuthController(
      UserPresenter userPresenter,
      AuthUser userResource,
      WebRepresentationConfig webRepresentationConfig) {
    this.userPresenter = userPresenter;
    this.userResource = userResource;
    webRepresentationClient = webRepresentationConfig.createClient();
  }

  @SecurityRequirement(name = "web")
  @Operation(summary = "Get current User in detail")
  @GetMapping("me")
  public ResponseEntity<UserDetailDto> getCurrentUserDetail() {
    return userPresenter.presentUserDetail(userResource.getCurrentUserDetail());
  }

  @Operation(summary = "Get Public Client Token")
  @GetMapping("token/client-token")
  public ResponseEntity<TokenDto> getClientToken() {
    final AccessTokenResponse accToken = webRepresentationClient.obtainAccessToken();
    return ResponseEntity.ok(
        new TokenDto(accToken.getToken(), accToken.getTokenType(), accToken.getExpiresIn()));
  }
}
