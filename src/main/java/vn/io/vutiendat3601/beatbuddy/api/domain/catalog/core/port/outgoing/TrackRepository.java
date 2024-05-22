package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing;

import java.util.List;
import java.util.Optional;

import vn.io.vutiendat3601.beatbuddy.api.common.repository.SearchableRepository;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;

public interface TrackRepository extends SearchableRepository<Track> {
  Optional<Track> findById(String id);

  List<Track> findByIds(Iterable<String> ids);

  Pagination<Track> findByArtistsIdOrderByTotalLikesDesc(String artistId, int page, int size);

  Pagination<Track> findByOrderByTotalLikesDesc(int page, int size);
}
