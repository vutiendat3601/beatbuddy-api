package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_TYPE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_TYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.common.dto.ResponseDto;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.ArtistMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.PlaylistMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.TrackMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.UserMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ArtistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.SearchDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.TrackDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

@Component
@RequiredArgsConstructor
public class CatalogPresenter {
  /* #: Common */
  public ResponseEntity<ResponseDto> presentResponseOk(String message) {
    return ResponseEntity.ok(new ResponseDto(message, HttpStatus.OK));
  }

  /* # Common */

  /* #: Search */
  public ResponseEntity<SearchDto> presentSearch(
      Set<Pagination<?>> results, Set<String> types, Integer page, Integer size) {
    final Map<String, Pagination<?>> resultsMap = new HashMap<>();
    results.forEach(
        result -> {
          if (!result.getItems().isEmpty()) {
            if (result.getItems().get(0) instanceof Track) {
              resultsMap.put(TRACK_TYPE, result.map(t -> TrackMapper.mapToTrackDto((Track) t)));
            } else if (result.getItems().get(0) instanceof Artist) {
              resultsMap.put(ARTIST_TYPE, result.map(a -> ArtistMapper.mapToArtistDto((Artist) a)));
            }
          }
        });
    types.forEach(
        type -> {
          if (!resultsMap.containsKey(type)) {
            resultsMap.put(type, Pagination.empty());
          }
        });
    return ResponseEntity.ok(new SearchDto(resultsMap, types, page, size));
  }

  /* # Search */

  /* #: User */
  public ResponseEntity<UserDto> presentUser(User user) {
    return ResponseEntity.ok(UserMapper.mapToUserDto(user));
  }

  /* # User */
  /* #: Track */
  public ResponseEntity<TrackDto> presentTrack(Track track) {
    final TrackDto trackDto = TrackMapper.mapToTrackDto(track);
    return ResponseEntity.ok(trackDto);
  }

  public ResponseEntity<List<TrackDto>> presentTracks(List<Track> tracks) {
    final List<TrackDto> trackDtos = tracks.stream().map(TrackMapper::mapToTrackDto).toList();
    return ResponseEntity.ok(trackDtos);
  }

  public ResponseEntity<Pagination<TrackDto>> presentTrackPage(Pagination<Track> trackPage) {
    Pagination<TrackDto> trackDtoPage = trackPage.map(TrackMapper::mapToTrackDto);
    return ResponseEntity.ok(trackDtoPage);
  }

  /* # Track */

  /* #: Artist */
  public ResponseEntity<ArtistDto> presentArtist(Artist artist) {
    ArtistDto artistDto = ArtistMapper.mapToArtistDto(artist);
    return ResponseEntity.ok(artistDto);
  }

  public ResponseEntity<List<ArtistDto>> presentArtists(List<Artist> artists) {
    List<ArtistDto> artistDtos = artists.stream().map(ArtistMapper::mapToArtistDto).toList();
    return ResponseEntity.ok(artistDtos);
  }

  public ResponseEntity<Pagination<ArtistDto>> presentArtistPage(Pagination<Artist> artistPage) {
    Pagination<ArtistDto> artistDtoPage = artistPage.map(ArtistMapper::mapToArtistDto);
    return ResponseEntity.ok(artistDtoPage);
  }

  /* # Artist */

  /* #: Playlist */
  public ResponseEntity<PlaylistDto> presentPlaylist(Playlist playlist) {
    PlaylistDto playlistDto = PlaylistMapper.mapToPlaylistDto(playlist);
    return ResponseEntity.ok(playlistDto);
  }

  public ResponseEntity<Pagination<PlaylistDto>> presentPlaylistPage(
      Pagination<Playlist> playlistPage) {
    Pagination<PlaylistDto> playlistDtosPage = playlistPage.map(PlaylistMapper::mapToPlaylistDto);
    return ResponseEntity.ok(playlistDtosPage);
  }
  /* # Playlist */
}
