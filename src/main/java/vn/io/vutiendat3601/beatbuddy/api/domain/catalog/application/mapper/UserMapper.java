package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

public interface UserMapper {
  static UserDto mapToUserDto(User user) {
    return UserDto.builder()
        .id(user.id())
        .urn(user.urn())
        .firstName(user.firstName())
        .lastName(user.lastName())
        .picture(user.picture())
        .build();
  }
}
