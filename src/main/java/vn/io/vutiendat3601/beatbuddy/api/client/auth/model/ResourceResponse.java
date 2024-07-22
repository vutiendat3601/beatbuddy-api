package vn.io.vutiendat3601.beatbuddy.api.client.auth.model;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.type.ResourceUser;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.type.ScopePermisison;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceResponse {
  private String urn;

  private String name;

  private String type;

  private ResourceUser owner;

  @Builder.Default private Set<String> uris = new HashSet<>();

  @Builder.Default private Set<String> scopes = new HashSet<>();

  @Builder.Default private Set<ScopePermisison> scopePermissions = new HashSet<>();
}
