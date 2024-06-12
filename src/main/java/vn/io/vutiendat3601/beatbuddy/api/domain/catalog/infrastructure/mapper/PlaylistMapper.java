package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper;

import java.util.LinkedList;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.PlaylistPo;

public interface PlaylistMapper {
  static Playlist mapToPlaylist(PlaylistPo playlistPo) {
    return new Playlist(
        playlistPo.getPkId(),
        playlistPo.getId(),
        playlistPo.getUrn(),
        playlistPo.getName(),
        playlistPo.getThumbnail(),
        playlistPo.getDescription(),
        playlistPo.getIsPublic(),
        playlistPo.getTotalLikes(),
        playlistPo.getOwnerId(),
        null,
        playlistPo.getIsDeleted(),
        new LinkedList<>(playlistPo.getItemUrns()),
        new LinkedList<>());
  }
}
