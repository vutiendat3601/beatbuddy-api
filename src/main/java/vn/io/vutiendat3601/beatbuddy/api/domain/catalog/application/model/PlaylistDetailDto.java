package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.PlaylistItem;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.ResourceUser;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.ScopePermission;

@Schema(name = "PlaylistDetail", description = "Schema holding Playlist data in detail")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaylistDetailDto {
  @Schema(name = "id", description = "Id")
  private String id;

  @Schema(name = "urn", description = "Urn")
  private String urn;

  @Schema(name = "name", description = "Name")
  private String name;

  @Schema(name = "thumbnail", description = "Thumbnail")
  private String thumbnail;

  @Schema(name = "description", description = "Description")
  private String description;

  @Schema(name = "isPublic", description = "Is public")
  private Boolean isPublic;

  @Schema(name = "owner", description = "Owner")
  private ResourceUser owner;

  @Schema(name = "totalLikes", description = "Total likes")
  private Long totalLikes;

  @Schema(name = "items", description = "List of items")
  @Builder.Default
  private List<PlaylistItem> items = new LinkedList<>();

  @Schema(name = "scopePermissions", description = "Scope permissions")
  @Builder.Default
  private Set<ScopePermission> scopePermissions = new HashSet<>();
}
