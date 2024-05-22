package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model;

import java.util.LinkedList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Playlist", description = "Schema holding Playlist data")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaylistDto {
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

  @Schema(name = "ownerId", description = "Owner id")
  private String ownerId;

  @Schema(name = "totalLikes", description = "Total likes")
  private Long totalLikes;

  @Schema(name = "itemUrns", description = "List of item urns")
  @Builder.Default
  private List<String> itemUrns = new LinkedList<>();
}
