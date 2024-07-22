package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.TrackMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.TrackDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;

@RequiredArgsConstructor
@Component
public class TrackPresenter {
  private final TrackMapper trackMapper;

  public ResponseEntity<TrackDto> presentTrackDto(Track track) {
    final TrackDto trackDto = trackMapper.mapToTrackDto(track);
    return ResponseEntity.ok(trackDto);
  }

  public ResponseEntity<List<TrackDto>> presentTrackDtos(List<Track> tracks) {
    final List<TrackDto> trackDtos = tracks.stream().map(trackMapper::mapToTrackDto).toList();
    return ResponseEntity.ok(trackDtos);
  }

  public ResponseEntity<Pagination<TrackDto>> presentTrackDtoPage(Pagination<Track> trackPage) {
    Pagination<TrackDto> trackDtoPage = trackPage.map(trackMapper::mapToTrackDto);
    return ResponseEntity.ok(trackDtoPage);
  }
}
