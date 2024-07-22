package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.UserMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

@Component("catalogUserPresenter")
@RequiredArgsConstructor
public class UserPresenter {
  private final UserMapper userMapper;

  public ResponseEntity<UserDto> presentUserDto(User user) {
    return ResponseEntity.ok(userMapper.mapToUserDto(user));
  }
}
