package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.model;

import org.keycloak.representations.idm.UserRepresentation;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPo {
  private UserRepresentation userRepresentation;
}
