package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.PlaylistItem;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.ResourceUser;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.ScopePermission;

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

  @Builder.Default private Boolean isPublic = false;

  @Builder.Default private Long totalLikes = 0L;

  private String ownerId;

  private ResourceUser owner;

  private Set<ScopePermission> scopePermissions;

  @Builder.Default private Boolean isDeleted = false;

  @Builder.Default private List<String> itemUrns = new LinkedList<>();

  @Builder.Default private List<PlaylistItem> items = new LinkedList<>();

  public Playlist(UUID pkId) {
    this.pkId = pkId;
  }

  public void addItemUrnsAtHead(List<String> itemUrns) {
    this.itemUrns.addAll(0, itemUrns);
  }
}
