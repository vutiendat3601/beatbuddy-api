package vn.io.vutiendat3601.beatbuddy.api.application.config;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.OPENIDCONNECT;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(
    name = "web",
    openIdConnectUrl =
        "https://auth.beatbuddy.io.vn/realms/beatbuddy/.well-known/openid-configuration",
    scheme = "Bearer",
    bearerFormat = "JWT",
    type = OPENIDCONNECT,
    in = HEADER)
@OpenAPIDefinition(
    info = @Info(title = "BeatBuddy APIs", version = "1.0", description = "BeatBuddy APIs"))
public class OpenApiConfig {}
