package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.IdentityProvider;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  private String pkId;

  private String id;

  private String urn;

  private String firstName;

  private String lastName;

  private String username;

  private String email;

  private Boolean isEmailVerified;

  private String picture;

  @Builder.Default private Set<IdentityProvider> linkedIdentityProviders = new HashSet<>();
}
