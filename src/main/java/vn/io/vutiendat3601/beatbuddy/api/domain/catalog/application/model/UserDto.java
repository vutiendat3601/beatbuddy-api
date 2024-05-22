package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "User", description = "Schema holding user information")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  @Schema(description = "User id")
  private String id;

  @Schema(description = "User URN")
  private String urn;

  @Schema(description = "User first name")
  private String firstName;

  @Schema(description = "User last name")
  private String lastName;

  @Schema(description = "User profile image URL")
  private String picture;
}
