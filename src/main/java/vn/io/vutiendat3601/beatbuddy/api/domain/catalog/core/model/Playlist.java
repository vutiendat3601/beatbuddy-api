package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_URN_PREFIX;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public record Playlist(
    UUID pkId,
    String id,
    String urn,
    String name,
    String thumbnail,
    String description,
    Boolean isPublic,
    Long totalLikes,
    String ownerId,
    List<String> itemUrns) {

  public Playlist(String id, String name, String ownerId) {
    this(
        null,
        id,
        PLAYLIST_URN_PREFIX + ":" + id,
        name,
        "",
        "",
        true,
        0L,
        ownerId,
        new LinkedList<>());
    if (id == null || name == null || ownerId == null) {
      throw new IllegalArgumentException("[id, name, ownerUrn] is required");
    }
  }

  public Playlist(UUID pkId) {
    this(pkId, null, null, null, null, null, null, null, null, new LinkedList<>());
  }
}
