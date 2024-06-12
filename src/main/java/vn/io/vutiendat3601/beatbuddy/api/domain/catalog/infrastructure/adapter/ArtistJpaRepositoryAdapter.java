package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.common.type.SearchRequest;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.ArtistRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper.ArtistMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.ArtistPo;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository.ArtistJpaRepository;

@Component
@RequiredArgsConstructor
public class ArtistJpaRepositoryAdapter implements ArtistRepository {
  private final ArtistJpaRepository artistJpaRepo;

  public Optional<Artist> findById(String id) {
    return artistJpaRepo.findById(id).map(ArtistMapper::mapToArtist);
  }

  public List<Artist> findByIds(Iterable<String> ids) {
    return artistJpaRepo.findByIdIn(ids).stream().map(ArtistMapper::mapToArtist).toList();
  }

  @Override
  public Pagination<Artist> findByOrderByTotalLikesDesc(int page, Integer size) {
    final Page<ArtistPo> artistPoPage =
        artistJpaRepo.findByOrderByTotalLikesDesc(PageRequest.of(page, size));
    final Page<Artist> artistPage = artistPoPage.map(ArtistMapper::mapToArtist);
    return Pagination.of(artistPage);
  }

  @Override
  public Pagination<Artist> findBySearchRequest(SearchRequest searchReq) {
    final Pageable pageReq = PageRequest.of(searchReq.getPage(), searchReq.getSize());
    final String tokens[] = searchReq.getQuery().split("\s+");
    final String tsvQuery = String.join("&", tokens);
    Page<ArtistPo> artistPoPage =
        artistJpaRepo.findByTagOrTsvOrderByTotalLikesDesc(searchReq.getQuery(), tsvQuery, pageReq);
    Page<Artist> artistPage = artistPoPage.map(ArtistMapper::mapToArtist);
    return Pagination.of(artistPage);
  }
}
