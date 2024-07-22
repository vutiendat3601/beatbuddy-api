package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming;

import java.util.List;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;

public interface ArtistService {
  Pagination<Artist> getPopularArtists(int page, int size);

  List<Artist> getArtistByIds(List<String> ids);

  Artist getArtistById(String id);

  void likeArtist(String id);

  void unlikeArtist(String id);
}
