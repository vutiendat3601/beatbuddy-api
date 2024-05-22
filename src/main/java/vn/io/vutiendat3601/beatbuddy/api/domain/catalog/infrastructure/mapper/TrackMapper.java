package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.TrackPo;

public interface TrackMapper {
  static Track mapToTrack(TrackPo trackPo) {
    return new Track(
        trackPo.getPkId(),
        trackPo.getId(),
        trackPo.getUrn(),
        trackPo.getName(),
        trackPo.getDurationSec(),
        trackPo.getDescription(),
        trackPo.getReleasedDate(),
        trackPo.getThumbnail(),
        trackPo.getIsPublic(),
        trackPo.getIsPlayable(),
        trackPo.getTotalLikes(),
        trackPo.getAudioFileIds(),
        trackPo.getTags(),
        trackPo.getArtists().stream().map(ArtistMapper::mapToArtist).toList());
  }
}
