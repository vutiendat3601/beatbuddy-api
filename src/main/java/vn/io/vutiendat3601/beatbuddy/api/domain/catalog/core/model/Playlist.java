package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.PlaylistItem;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Playlist {
  private UUID pkId;

  private String id;

  private String urn;

  private String name;

  private String thumbnail;

  private String description;

  private Boolean isPublic;

  private Long totalLikes;

  private String ownerId;

  private User owner;

  private Boolean isDeleted;

  @Builder.Default private List<String> itemUrns = new LinkedList<>();

  @Builder.Default private List<PlaylistItem> items = new LinkedList<>();

  public Playlist(String id, String name, String ownerId) {
    this.id = id;
    this.name = name;
    this.ownerId = ownerId;
  }

  public Playlist(UUID pkId) {
    this.pkId = pkId;
  }

  public void addItemUrnsAtHead(List<String> itemUrns) {
    this.itemUrns.addAll(0, itemUrns);
  }
}
