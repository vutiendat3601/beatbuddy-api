package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDetailDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;

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

  static UserDetailDto mapToUserDetailDto(User user) {
    return UserDetailDto.builder()
        .id(user.id())
        .urn(user.urn())
        .firstName(user.firstName())
        .lastName(user.lastName())
        .picture(user.picture())
        .username(user.username())
        .email(user.email())
        .isEmailVerified(user.isEmailVerified())
        .linkedIdentityProviders(user.linkedIdentityProviders())
        .build();
  }
}
