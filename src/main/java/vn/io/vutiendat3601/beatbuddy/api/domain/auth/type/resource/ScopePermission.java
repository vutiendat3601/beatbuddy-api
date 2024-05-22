package vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScopePermission {
  private String userId;

  private String scope;

  private boolean isGranted;
}
