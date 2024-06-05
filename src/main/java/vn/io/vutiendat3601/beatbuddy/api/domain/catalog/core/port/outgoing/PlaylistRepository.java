package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing;

import java.util.Optional;

import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;

public interface PlaylistRepository {
  void save(Playlist playlist);

  Optional<Playlist> findById(String id);

  Optional<Playlist> findByIdAndIsPublicTrue(String id);

  Pagination<Playlist> findAllByOwnerId(String ownerId, int page, int size);
}
