package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.model;

import org.keycloak.representations.idm.authorization.PermissionTicketRepresentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScopePermissionPo {
  private PermissionTicketRepresentation permissionTicketRepresentation;

  private String userId;
}
