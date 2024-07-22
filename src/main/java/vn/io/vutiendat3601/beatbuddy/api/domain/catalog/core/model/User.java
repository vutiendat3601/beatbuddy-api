package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  private String id;

  private String urn;

  private String firstName;

  private String lastName;

  private String picture;
}
