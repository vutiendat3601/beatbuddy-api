package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenDto {
  private String token;

  private String tokenType;

  private long expiresIn;
}
