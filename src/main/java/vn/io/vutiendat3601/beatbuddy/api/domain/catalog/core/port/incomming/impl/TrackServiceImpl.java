package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.impl;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_NOT_FOUND;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.TrackNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.CatalogService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.TrackService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.TrackRepository;

@RequiredArgsConstructor
@Service
public class TrackServiceImpl implements TrackService {
  private final TrackRepository trackRepo;
  private final CatalogService catalogService;

  @Override
  public Track getTrackById(String id) {
    return trackRepo.findById(id).orElseThrow(() -> new TrackNotFoundException(TRACK_NOT_FOUND));
  }

  @Override
  public List<Track> getTrackByIds(List<String> ids) {
    return trackRepo.findByIds(ids);
  }

  @Override
  public Pagination<Track> getArtistPopularTracks(String id, int page, int size) {
    return trackRepo.findByArtistsIdOrderByTotalLikesDesc(id, page, size);
  }

  @Override
  public Pagination<Track> getPopularTracks(int page, int size) {
    return trackRepo.findByOrderByTotalLikesDesc(page, size);
  }

  @Override
  public void likeTrack(String id) {
    final Track track =
        trackRepo.findById(id).orElseThrow(() -> new TrackNotFoundException(TRACK_NOT_FOUND));
    catalogService.addLike(track.getUrn());
  }

  @Override
  public void unlikeTrack(String id) {
    final Track track =
        trackRepo.findById(id).orElseThrow(() -> new TrackNotFoundException(TRACK_NOT_FOUND));
    catalogService.removeLike(track.getUrn());
  }
}
