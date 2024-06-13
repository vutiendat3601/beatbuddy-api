package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDetailDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;

public interface UserMapper {
  static UserDto mapToUserDto(User user) {
    return UserDto.builder()
        .id(user.getId())
        .urn(user.getUrn())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .picture(user.getPicture())
        .build();
  }

  static UserDetailDto mapToUserDetailDto(User user) {
    return UserDetailDto.builder()
        .id(user.getId())
        .urn(user.getUrn())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .picture(user.getPicture())
        .username(user.getUsername())
        .email(user.getEmail())
        .isEmailVerified(user.getIsEmailVerified())
        .linkedIdentityProviders(user.getLinkedIdentityProviders())
        .build();
  }
}
