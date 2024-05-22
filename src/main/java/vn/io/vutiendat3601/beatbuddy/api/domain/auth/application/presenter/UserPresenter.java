package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.presenter;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.mapper.UserMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDetailDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;

@Component
public class UserPresenter {
  public ResponseEntity<UserDto> presentUser(User user) {
    return ResponseEntity.ok(UserMapper.mapToUserDto(user));
  }

  public ResponseEntity<List<UserDto>> presentUsers(Iterable<User> users) {
    return ResponseEntity.ok(
        StreamSupport.stream(users.spliterator(), true).map(UserMapper::mapToUserDto).toList());
  }

  public ResponseEntity<UserDetailDto> presentUserDetail(User user) {
    return ResponseEntity.ok(UserMapper.mapToUserDetailDto(user));
  }
}
