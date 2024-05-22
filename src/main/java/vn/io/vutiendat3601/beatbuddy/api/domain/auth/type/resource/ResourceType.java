package vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource;

import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResourceType {
  private String name;

  private Set<String> uriPrefixes;

  private Set<String> ownerScopes;

  private Set<String> requesterScopes;
}
