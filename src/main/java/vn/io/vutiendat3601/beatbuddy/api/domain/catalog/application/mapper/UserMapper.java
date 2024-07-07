package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

@Component("catalogUserMapper")
public class UserMapper {
  public UserDto mapToUserDto(User user) {
    return UserDto.builder()
        .id(user.getId())
        .urn(user.getUrn())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .picture(user.getPicture())
        .build();
  }
}
