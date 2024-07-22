package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ResourceUser;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ScopePermission;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
  private String pkId;

  private String urn;

  private String name;

  private String type;

  private ResourceUser owner;

  private Set<String> uris;

  private Set<String> scopes;

  @Builder.Default private Set<ScopePermission> scopePermissions = new HashSet<>();
}
