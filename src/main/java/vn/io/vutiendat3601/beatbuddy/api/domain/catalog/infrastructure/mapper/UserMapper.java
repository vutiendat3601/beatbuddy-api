package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper;

import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.UserDetailResponse;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.UserResponse;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

public interface UserMapper {
  static User mapToUser(UserResponse userResp) {
    return new User(
        userResp.getId(),
        userResp.getUrn(),
        userResp.getFirstName(),
        userResp.getLastName(),
        userResp.getPicture());
  }

  static User mapToUser(UserDetailResponse userDetailResp) {
    return new User(
        userDetailResp.getId(),
        userDetailResp.getUrn(),
        userDetailResp.getFirstName(),
        userDetailResp.getLastName(),
        userDetailResp.getPicture());
  }
}
