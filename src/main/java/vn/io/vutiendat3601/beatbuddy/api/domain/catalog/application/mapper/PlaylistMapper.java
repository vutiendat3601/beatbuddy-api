package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;

public interface PlaylistMapper {
  static PlaylistDto mapToPlaylistDto(Playlist playlist) {
    return PlaylistDto.builder()
        .id(playlist.id())
        .urn(playlist.urn())
        .name(playlist.name())
        .description(playlist.description())
        .thumbnail(playlist.thumbnail())
        .isPublic(playlist.isPublic())
        .totalLikes(playlist.totalLikes())
        .itemUrns(playlist.itemUrns())
        .ownerId(playlist.ownerId())
        .build();
  }
}
