package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Artist {
  private UUID pkId;

  private String id;

  private String urn;

  private String name;

  private Boolean isVerified;

  private Boolean isPublic;

  private LocalDate birthDate;

  private String description;

  private String nationality;

  private String biography;

  private String thumbnail;

  private String background;

  private String tags;

  private Long totalLikes;
}
