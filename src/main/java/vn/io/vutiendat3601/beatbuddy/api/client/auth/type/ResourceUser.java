package vn.io.vutiendat3601.beatbuddy.api.client.auth.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResourceUser {
  private String id;

  private String urn;

  private String firstName;

  private String lastName;

  private String picture;
}
