package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {
  private UUID pkId;

  private String id;

  private String urn;

  private String name;

  private Integer durationSec;

  private String description;

  private String releasedDate;

  private String thumbnail;

  private Boolean isPublic;

  private Boolean isPlayable;

  private Long totalLikes;

  @Builder.Default private Set<String> audioFileIds = new HashSet<>();

  private String tags;

  @Builder.Default private List<Artist> artists = new LinkedList<>();
}
