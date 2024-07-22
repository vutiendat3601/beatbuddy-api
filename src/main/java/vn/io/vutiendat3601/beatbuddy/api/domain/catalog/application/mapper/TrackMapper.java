package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.TrackDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;

@RequiredArgsConstructor
@Component
public class TrackMapper {
  private final ArtistMapper artistMapper;

  public TrackDto mapToTrackDto(Track track) {
    return TrackDto.builder()
        .id(track.getId())
        .urn(track.getUrn())
        .name(track.getName())
        .durationSec(track.getDurationSec())
        .description(track.getDescription())
        .releasedDate(track.getReleasedDate())
        .thumbnail(track.getThumbnail())
        .isPublic(track.getIsPublic())
        .isPlayable(track.getIsPlayable())
        .totalLikes(track.getTotalLikes())
        .audioFileIds(track.getAudioFileIds())
        .artists(track.getArtists().stream().map(artistMapper::mapToArtistDto).toList())
        .build();
  }
}
