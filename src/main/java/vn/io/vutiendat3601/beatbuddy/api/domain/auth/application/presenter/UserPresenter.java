package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.presenter;

import java.util.List;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.mapper.UserMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDetailDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;

@RequiredArgsConstructor
@Component("authUserPresenter")
public class UserPresenter {
  private final UserMapper userMapper;

  public ResponseEntity<UserDto> presentUser(User user) {
    return ResponseEntity.ok(userMapper.mapToUserDto(user));
  }

  public ResponseEntity<List<UserDto>> presentUsers(Iterable<User> users) {
    return ResponseEntity.ok(
        StreamSupport.stream(users.spliterator(), true).map(userMapper::mapToUserDto).toList());
  }

  public ResponseEntity<UserDetailDto> presentUserDetail(User user) {
    return ResponseEntity.ok(userMapper.mapToUserDetailDto(user));
  }
}
