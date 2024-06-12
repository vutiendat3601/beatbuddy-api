package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ArtistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;

public interface ArtistMapper {
  static ArtistDto mapToArtistDto(Artist artist) {
    return ArtistDto.builder()
        .id(artist.getId())
        .urn(artist.getUrn())
        .name(artist.getName())
        .isVerified(artist.getIsVerified())
        .isPublic(artist.getIsPublic())
        .birthDate(artist.getBirthDate())
        .description(artist.getDescription())
        .nationality(artist.getNationality())
        .biography(artist.getBiography())
        .thumbnail(artist.getThumbnail())
        .background(artist.getBackground())
        .totalLikes(artist.getTotalLikes())
        .build();
  }
}
