package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDetailDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;

public interface PlaylistMapper {
  static PlaylistDto mapToPlaylistDto(Playlist playlist) {
    return PlaylistDto.builder()
        .id(playlist.getId())
        .urn(playlist.getUrn())
        .name(playlist.getName())
        .description(playlist.getDescription())
        .thumbnail(playlist.getThumbnail())
        .isPublic(playlist.getIsPublic())
        .totalLikes(playlist.getTotalLikes())
        .itemUrns(playlist.getItemUrns())
        .ownerId(playlist.getOwnerId())
        .build();
  }

  static PlaylistDetailDto mapToPlaylistDetailDto(Playlist playlist) {
    return PlaylistDetailDto.builder()
        .id(playlist.getId())
        .urn(playlist.getUrn())
        .name(playlist.getName())
        .description(playlist.getDescription())
        .thumbnail(playlist.getThumbnail())
        .isPublic(playlist.getIsPublic())
        .totalLikes(playlist.getTotalLikes())
        .items(playlist.getItems())
        .owner(playlist.getOwner())
        .scopePermissions(playlist.getScopePermissions())
        .items(playlist.getItems())
        .build();
  }
}
