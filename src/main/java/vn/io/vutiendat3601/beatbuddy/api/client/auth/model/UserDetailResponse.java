package vn.io.vutiendat3601.beatbuddy.api.client.auth.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource.IdentityProvider;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {
  private String id;

  private String urn;

  private String firstName;

  private String lastName;

  private String picture;

  private String username;

  private String email;

  private Boolean isEmailVerified;

  private Set<IdentityProvider> linkedIdentityProviders;
}
