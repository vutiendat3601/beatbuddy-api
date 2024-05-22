package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.TrackDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;

public interface TrackMapper {
  static TrackDto mapToTrackDto(Track track) {
    return TrackDto.builder()
        .id(track.id())
        .urn(track.urn())
        .name(track.name())
        .durationSec(track.durationSec())
        .description(track.description())
        .releasedDate(track.releasedDate())
        .thumbnail(track.thumbnail())
        .isPublic(track.isPublic())
        .isPlayable(track.isPlayable())
        .totalLikes(track.totalLikes())
        .audioFileIds(track.audioFileIds())
        .artists(track.artists().stream().map(ArtistMapper::mapToArtistDto).toList())
        .build();
  }
}
