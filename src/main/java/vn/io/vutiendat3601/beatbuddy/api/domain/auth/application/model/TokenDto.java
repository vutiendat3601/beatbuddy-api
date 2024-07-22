package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Token", description = "Schema holding Token data")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenDto {
  @Schema(name = "token", description = "Token value")
  private String token;

  @Schema(name = "tokenType", description = "Token type")
  private String tokenType;

  @Schema(name = "expiresIn", description = "Token expiration time in miliseconds")
  private long expiresIn;
}
