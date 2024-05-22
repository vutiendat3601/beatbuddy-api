package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing;

import java.util.List;
import java.util.Optional;

import vn.io.vutiendat3601.beatbuddy.api.common.repository.SearchableRepository;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;

public interface ArtistRepository extends SearchableRepository<Artist> {
  Optional<Artist> findById(String id);

  List<Artist> findByIds(Iterable<String> ids);

  Pagination<Artist> findByOrderByTotalLikesDesc(int page, Integer size);

  // Pagination<Artist> findBySearchRequest(SearchRequest searchReq);
}
