package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.common.type.SearchRequest;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.TrackRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper.TrackMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.TrackPo;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository.TrackJpaRepository;

@RequiredArgsConstructor
@Repository
public class TrackJpaRepositoryAdapter implements TrackRepository {
  private final TrackJpaRepository trackJpaRepo;

  @Override
  public Optional<Track> findById(String id) {
    return trackJpaRepo.findById(id).map(TrackMapper::mapToTrack);
  }

  @Override
  public List<Track> findByIds(Iterable<String> ids) {
    return trackJpaRepo.findByIdIn(ids).stream().map(TrackMapper::mapToTrack).toList();
  }

  @Override
  public Pagination<Track> findByArtistsIdOrderByTotalLikesDesc(
      String artistId, int page, int size) {
    Page<TrackPo> trackPoPage =
        trackJpaRepo.findByArtistsIdOrderByTotalLikesDesc(artistId, PageRequest.of(page, size));
    Page<Track> trackPage = trackPoPage.map(TrackMapper::mapToTrack);
    return Pagination.of(trackPage);
  }

  @Override
  public Pagination<Track> findByOrderByTotalLikesDesc(int page, int size) {
    Page<TrackPo> trackPoPage =
        trackJpaRepo.findByOrderByTotalLikesDesc(PageRequest.of(page, size));
    Page<Track> trackPage = trackPoPage.map(TrackMapper::mapToTrack);
    return Pagination.of(trackPage);
  }

  @Override
  public Pagination<Track> findBySearchRequest(SearchRequest searchReq) {
    final Pageable pageReq = PageRequest.of(searchReq.getPage(), searchReq.getSize());
    final String tokens[] = searchReq.getQuery().split("\s+");
    final String tsvQuery = String.join("&", tokens);

    final Page<TrackPo> trackPoPage =
        trackJpaRepo.findByTagOrTsvOrderByTotalLikesDesc(searchReq.getQuery(), tsvQuery, pageReq);
    final Page<Track> trackPage = trackPoPage.map(TrackMapper::mapToTrack);
    return Pagination.of(trackPage);
  }
}
