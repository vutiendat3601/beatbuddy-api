package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.ArtistMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ArtistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;

@RequiredArgsConstructor
@Component
public class ArtistPresenter {
  private final ArtistMapper artistMapper;

  public ResponseEntity<ArtistDto> presentArtistDto(Artist artist) {
    ArtistDto artistDto = artistMapper.mapToArtistDto(artist);
    return ResponseEntity.ok(artistDto);
  }

  public ResponseEntity<List<ArtistDto>> presentArtistDtos(List<Artist> artists) {
    List<ArtistDto> artistDtos = artists.stream().map(artistMapper::mapToArtistDto).toList();
    return ResponseEntity.ok(artistDtos);
  }

  public ResponseEntity<Pagination<ArtistDto>> presentArtistDtoPage(Pagination<Artist> artistPage) {
    Pagination<ArtistDto> artistDtoPage = artistPage.map(artistMapper::mapToArtistDto);
    return ResponseEntity.ok(artistDtoPage);
  }
}
