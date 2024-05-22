package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ArtistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;

public interface ArtistMapper {
  static ArtistDto mapToArtistDto(Artist artist) {
    return ArtistDto.builder()
        .id(artist.id())
        .urn(artist.urn())
        .name(artist.name())
        .isVerified(artist.isVerified())
        .isPublic(artist.isPublic())
        .birthDate(artist.birthDate())
        .description(artist.description())
        .nationality(artist.nationality())
        .biography(artist.biography())
        .thumbnail(artist.thumbnail())
        .background(artist.background())
        .totalLikes(artist.totalLikes())
        .build();
  }
}
