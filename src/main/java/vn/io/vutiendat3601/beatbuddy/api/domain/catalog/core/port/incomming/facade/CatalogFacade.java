package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.facade;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_NOT_FOUND;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_NOT_FOUND;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_TYPE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_ID_LENGTH;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_NOT_FOUND;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_URN_PREFIX;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_NOT_FOUND;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_TYPE;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.AuthClient;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.UserResponse;
import vn.io.vutiendat3601.beatbuddy.api.common.repository.SearchableRepository;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.common.type.SearchRequest;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.ArtistNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.PlaylistNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.TrackNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.UserNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.Catalog;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.ArtistRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.PlaylistRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.TrackRepository;
import vn.io.vutiendat3601.beatbuddy.api.util.StringUtils;
import vn.io.vutiendat3601.beatbuddy.api.util.UserContext;

@Service
public class CatalogFacade implements Catalog {
  private final TrackRepository trackRepo;
  private final ArtistRepository artistRepo;
  private final PlaylistRepository playlistRepo;
  private final AuthClient authClient;
  private final Map<String, SearchableRepository<?>> searchableRepos;

  public CatalogFacade(
      TrackRepository trackRepo,
      ArtistRepository artistRepo,
      PlaylistRepository playlistRepo,
      AuthClient authClient) {
    this.trackRepo = trackRepo;
    this.artistRepo = artistRepo;
    this.playlistRepo = playlistRepo;
    this.authClient = authClient;
    searchableRepos =
        Map.of(
            TRACK_TYPE, trackRepo,
            ARTIST_TYPE, artistRepo);
  }

  /* #: User */
  @Override
  public User getUserById(String id) {
    final ResponseEntity<UserResponse> httpResp = authClient.getUser(id);
    if (httpResp.getStatusCode().is2xxSuccessful()) {
      final UserResponse userResp = httpResp.getBody();
      return new User(
          userResp.getId(),
          userResp.getUrn(),
          userResp.getFirstName(),
          userResp.getLastName(),
          userResp.getPicture());
    }
    throw new UserNotFoundException(USER_NOT_FOUND);
  }

  /* # User */

  /* #: Track */
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

  /* # Track */

  /* #: Artist */
  @Override
  public Artist getArtistById(String id) {
    return artistRepo.findById(id).orElseThrow(() -> new ArtistNotFoundException(ARTIST_NOT_FOUND));
  }

  @Override
  public List<Artist> getArtistByIds(List<String> ids) {
    return artistRepo.findByIds(ids);
  }

  @Override
  public Pagination<Artist> getPopularArtists(int page, int size) {
    return artistRepo.findByOrderByTotalLikesDesc(page, size);
  }

  /* # Artist */

  /* #: Playlist */
  @Override
  public void createPlaylist(String name, Boolean isPublic, String thumbnail, String description) {
    final String id = StringUtils.makeRandomString(PLAYLIST_ID_LENGTH);
    final String urn = PLAYLIST_URN_PREFIX + ":" + id;
    authClient.createResource(urn, name);
    final String ownerId = UserContext.getUserId();
    final Playlist playlist = new Playlist(id, name, ownerId);
    playlistRepo.save(playlist);
  }

  @Override
  public Playlist getPublicPlaylistById(String id) {
    final Playlist playlist =
        playlistRepo
            .findByIdAndIsPublicTrue(id)
            .orElseThrow(() -> new PlaylistNotFoundException(PLAYLIST_NOT_FOUND));
    return playlist;
  }

  @Override
  public Pagination<Playlist> getUserPlaylists(int page, int size) {
    final String ownerId = UserContext.getUserId();
    return playlistRepo.findAllByOwnerId(ownerId, page, size);
  }

  @Override
  public void addTrackToPlaylist(String id, List<String> trackIds) {
    final Playlist playlist =
        playlistRepo
            .findById(id)
            .orElseThrow(() -> new PlaylistNotFoundException(PLAYLIST_NOT_FOUND));
    playlist.addItemUrnsAtHead(trackIds);
    playlistRepo.save(playlist);
  }

  /* # Playlist */

  /* #: Search */
  @Override
  public Set<Pagination<?>> search(String query, Set<String> types, int page, int size) {
    query = StringUtils.removeAccent(query);
    final SearchRequest searchReq = new SearchRequest(query, page, size);
    final Set<Pagination<?>> results = new LinkedHashSet<>();
    types.stream()
        .forEach(
            type ->
                Optional.ofNullable(searchableRepos.get(type))
                    .ifPresent(repo -> results.add(repo.findBySearchRequest(searchReq))));
    return results;
  }

  /* # Search */
}
