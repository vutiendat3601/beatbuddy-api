package vn.io.vutiendat3601.beatbuddy.api.client.auth;

import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.ResourceResponse;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.Response;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.UserDetailResponse;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.UserResponse;

public interface AuthClient {
  /* #: User */
  ResponseEntity<UserDetailResponse> getCurrentUserDetail();

  ResponseEntity<UserResponse> getUser(String id);

  ResponseEntity<List<UserResponse>> getUsers(Set<String> urns);

  /* # User */

  /* #: Resource */

  ResponseEntity<Response> createResource(String urn, String name);

  ResponseEntity<ResourceResponse> getResource(String urn);
  /* # Resource */
}
