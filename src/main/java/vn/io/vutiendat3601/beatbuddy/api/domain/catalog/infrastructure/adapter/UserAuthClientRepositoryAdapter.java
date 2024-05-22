package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.adapter;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.client.AuthClient;
import vn.io.vutiendat3601.beatbuddy.api.client.model.UserDetailResponse;
import vn.io.vutiendat3601.beatbuddy.api.client.model.UserResponse;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.UserRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper.UserMapper;

@RequiredArgsConstructor
@Repository
public class UserAuthClientRepositoryAdapter implements UserRepository {
  private final AuthClient authClient;

  @Override
  public Optional<User> findById(String id) {
    ResponseEntity<UserResponse> httpResp = authClient.getUser(id);
    final UserResponse userResp = httpResp.getBody();
    return Optional.ofNullable(userResp).map(UserMapper::mapToUser);
  }

  @Override
  public Optional<User> findCurrentUser() {
    ResponseEntity<UserDetailResponse> httpResp = authClient.getCurrentUserDetail();
    final UserDetailResponse userResp = httpResp.getBody();
    return Optional.ofNullable(userResp).map(UserMapper::mapToUser);
  }
}
