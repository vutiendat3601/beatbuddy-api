package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing;

import java.util.Optional;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Like;

public interface LikeRepository {
  Optional<Like> findByOwnerId(String ownerId);

  void save(Like like);
}
