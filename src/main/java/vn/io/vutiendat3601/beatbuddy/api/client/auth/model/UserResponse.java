package vn.io.vutiendat3601.beatbuddy.api.client.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private String id;

  private String urn;

  private String firstName;

  private String lastName;

  private String picture;
}
