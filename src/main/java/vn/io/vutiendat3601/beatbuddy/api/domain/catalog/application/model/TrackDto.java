package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model;

import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Track", description = "Schema to hold Track information")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrackDto {
  @Schema(name = "id", description = "Unique identity of Track")
  private String id;

  @Schema(name = "urn", description = "Unique identity for resources in Beat Buddy")
  private String urn;

  @Schema(name = "name", description = "Name of track")
  private String name;

  @Schema(name = "durationSec", description = "Length of Track time")
  private Integer durationSec;

  @Schema(name = "description", description = "Description about Track")
  private String description;

  @Schema(
      name = "releasedDate",
      description = "The full date, year with month or only year Track released")
  private String releasedDate;

  @Schema(name = "thumbnail", description = "Thumbnail image url of Track")
  private String thumbnail;

  @Schema(name = "isPublic", description = "Track is visible to the world or not")
  private Boolean isPublic;

  @Schema(name = "isPlayable", description = "Track could be playable or not")
  private Boolean isPlayable;

  @Schema(name = "totalLikes", description = "Total likes of Track")
  private Long totalLikes;

  @Schema(name = "audioFileIds", description = "File identity of Track")
  private Set<String> audioFileIds;

  @Schema(name = "artists", description = "Artist list who perform Track")
  private List<ArtistDto> artists;
}
