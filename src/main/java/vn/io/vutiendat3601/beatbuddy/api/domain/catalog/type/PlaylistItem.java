package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PlaylistItem {
  private String id;

  private String urn;

  private String name;

  private Integer durationSec;

  private String description;

  private String releasedDate;

  private String thumbnail;

  private Boolean isPublic;

  private Boolean isPlayable;
  @Builder.Default private List<Artist> artists = new LinkedList<>();

  @NoArgsConstructor
  @Data
  @AllArgsConstructor
  public static class Artist {
    private String id;

    private String urn;

    private String name;

    private Boolean isPublic;

    private Boolean isVerified;

    private String description;

    private String thumbnail;
  }
}
