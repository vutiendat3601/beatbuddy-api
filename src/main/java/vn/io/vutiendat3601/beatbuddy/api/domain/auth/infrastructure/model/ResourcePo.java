package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.model;

import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourcePo {
  private ResourceRepresentation resourceRepresentation;

  private UserRepresentation owner;

  private Set<ScopePermissionPo> permissions = new LinkedHashSet<>();
}
