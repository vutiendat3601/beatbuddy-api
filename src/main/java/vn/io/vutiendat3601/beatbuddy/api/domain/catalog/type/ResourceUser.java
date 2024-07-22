package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceUser {
  private String id;

  private String urn;

  private String firstName;

  private String lastName;

  private String picture;
}
