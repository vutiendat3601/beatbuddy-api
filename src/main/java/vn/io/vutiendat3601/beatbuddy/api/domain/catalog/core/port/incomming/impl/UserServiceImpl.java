package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.impl;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.AuthClient;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.UserResponse;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.UserNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final AuthClient authClient;

  @Override
  public User getUserById(String id) {
    final ResponseEntity<UserResponse> httpResp = authClient.getUser(id);
    if (httpResp.getStatusCode().is2xxSuccessful()) {
      final UserResponse userResp = httpResp.getBody();
      return new User(
          userResp.getId(),
          userResp.getUrn(),
          userResp.getFirstName(),
          userResp.getLastName(),
          userResp.getPicture());
    }
    throw new UserNotFoundException(USER_NOT_FOUND);
  }
}
