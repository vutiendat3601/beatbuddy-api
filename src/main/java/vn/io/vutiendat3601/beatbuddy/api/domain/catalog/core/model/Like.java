package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Like {
  private UUID pkId;

  @Builder.Default private Set<String> urns = new HashSet<>();

  private String ownerId;

  public Like(String ownerId) {
    this.ownerId = ownerId;
  }
}
