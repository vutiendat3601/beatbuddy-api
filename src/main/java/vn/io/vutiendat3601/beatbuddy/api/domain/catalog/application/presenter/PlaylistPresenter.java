package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.PlaylistMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDetailDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;

@Component
@RequiredArgsConstructor
public class PlaylistPresenter {
  private final PlaylistMapper playlistMapper;

  public ResponseEntity<PlaylistDto> presentPlaylistDto(Playlist playlist) {
    final PlaylistDto playlistDto = playlistMapper.mapToPlaylistDto(playlist);
    return ResponseEntity.ok(playlistDto);
  }

  public ResponseEntity<PlaylistDetailDto> presentPlaylistDetailDto(Playlist playlist) {
    final PlaylistDetailDto playlistDetailDto = playlistMapper.mapToPlaylistDetailDto(playlist);
    return ResponseEntity.ok(playlistDetailDto);
  }

  public ResponseEntity<Pagination<PlaylistDto>> presentPlaylistDtoPage(
      Pagination<Playlist> playlistPage) {
    Pagination<PlaylistDto> playlistDtosPage = playlistPage.map(playlistMapper::mapToPlaylistDto);
    return ResponseEntity.ok(playlistDtosPage);
  }
}
