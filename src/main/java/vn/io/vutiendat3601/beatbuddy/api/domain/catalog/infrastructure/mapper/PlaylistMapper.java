package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.PlaylistPo;

public interface PlaylistMapper {
  static Playlist mapToPlaylist(PlaylistPo playlistPo) {
    return Playlist.builder()
        .pkId(playlistPo.getPkId())
        .id(playlistPo.getId())
        .urn(playlistPo.getUrn())
        .name(playlistPo.getName())
        .description(playlistPo.getDescription())
        .thumbnail(playlistPo.getThumbnail())
        .isPublic(playlistPo.getIsPublic())
        .totalLikes(playlistPo.getTotalLikes())
        .itemUrns(playlistPo.getItemUrns())
        .ownerId(playlistPo.getOwnerId())
        .build();
  }
}
