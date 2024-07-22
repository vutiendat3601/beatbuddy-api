package vn.io.vutiendat3601.beatbuddy.api.client.auth.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class ScopePermisison {
  private String scope;

  @Builder.Default private Boolean isGranted = false;

  private ResourceUser user;
}
