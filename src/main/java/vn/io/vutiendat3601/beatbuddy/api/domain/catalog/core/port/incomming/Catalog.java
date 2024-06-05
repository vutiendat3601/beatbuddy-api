package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming;

import java.util.List;
import java.util.Set;

import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

public interface Catalog {
  Track getTrackById(String id);

  List<Track> getTrackByIds(List<String> ids);

  Artist getArtistById(String id);

  List<Artist> getArtistByIds(List<String> ids);

  void createPlaylist(String name, Boolean isPublic, String thumbnail, String description);

  Playlist getPublicPlaylistById(String id);

  Pagination<Track> getArtistPopularTracks(String id, int page, int size);

  Pagination<Artist> getPopularArtists(int page, int size);

  Pagination<Track> getPopularTracks(int page, int size);

  Set<Pagination<?>> search(String query, Set<String> types, int page, int size);

  User getUserById(String id);

  Pagination<Playlist> getUserPlaylists(int page, int size);

  void addTrackToPlaylist(String id, List<String> trackIds);
}
