package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming;

import java.util.List;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;

public interface TrackService {
  Track getTrackById(String id);

  List<Track> getTrackByIds(List<String> ids);

  void likeTrack(String id);

  void unlikeTrack(String id);

  Pagination<Track> getArtistPopularTracks(String id, int page, int size);

  Pagination<Track> getPopularTracks(int page, int size);
}
