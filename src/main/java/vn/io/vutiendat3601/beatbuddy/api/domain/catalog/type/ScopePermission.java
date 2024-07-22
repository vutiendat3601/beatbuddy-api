package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScopePermission {
  private String scope;

  @Builder.Default private Boolean isGranted = false;

  private ResourceUser user;
}
