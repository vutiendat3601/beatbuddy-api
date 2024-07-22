package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.IdentityProvider;

@Schema(name = "UserDetail", description = "Schema holding user information in detail")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserDetailDto extends UserDto {
  @Schema(description = "Username of the user")
  private String username;

  @Schema(description = "Email of the user")
  private String email;

  private Boolean isEmailVerified;

  @Schema(description = "Linked identity providers of the user")
  private Set<IdentityProvider> linkedIdentityProviders;
}
