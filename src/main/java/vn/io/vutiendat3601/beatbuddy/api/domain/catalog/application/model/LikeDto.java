package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Like", description = "Schema holding Like resources data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {
  @Schema(name = "ownerId", description = "The onwer of this Like")
  private String ownerId;

  @Schema(name = "urns", description = "The list of URN resources liked by user")
  @Builder.Default
  private Set<String> urns = new HashSet<>();
}
