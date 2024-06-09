package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record Like(UUID pkId, Set<String> urns, String ownerId) {
  public Like(String ownerId) {
    this(null, new HashSet<>(), ownerId);
  }
}
