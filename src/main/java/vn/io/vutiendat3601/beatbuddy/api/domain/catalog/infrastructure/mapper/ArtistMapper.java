package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.ArtistPo;

public interface ArtistMapper {
  static Artist mapToArtist(ArtistPo artistPo) {
    return new Artist(
        artistPo.getPkId(),
        artistPo.getId(),
        artistPo.getUrn(),
        artistPo.getName(),
        artistPo.getIsVerified(),
        artistPo.getIsPublic(),
        artistPo.getBirthDate(),
        artistPo.getDescription(),
        artistPo.getNationality(),
        artistPo.getBiography(),
        artistPo.getThumbnail(),
        artistPo.getBackground(),
        artistPo.getTags(),
        artistPo.getTotalLikes());
  }
}
