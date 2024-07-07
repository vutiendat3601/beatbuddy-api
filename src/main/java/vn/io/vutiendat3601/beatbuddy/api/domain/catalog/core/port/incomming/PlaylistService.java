package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming;

import java.util.List;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;

public interface PlaylistService {
  Pagination<Playlist> getPopularPlaylists(int page, int size);

  void createPlaylist(String name, Boolean isPublic, String thumbnail, String description);

  Playlist getPlaylistById(String id);

  Pagination<Playlist> getUserPlaylists(int page, int size);

  void addItemToPlaylist(String id, List<String> trackIds);
}
