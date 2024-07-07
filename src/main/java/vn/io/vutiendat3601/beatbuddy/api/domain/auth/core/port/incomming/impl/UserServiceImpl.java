package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.impl;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_NOT_FOUND;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.exception.UserNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.UserService;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.outgoing.UserRepository;
import vn.io.vutiendat3601.beatbuddy.api.util.UserContext;

@RequiredArgsConstructor
@Service("authUserService")
public class UserServiceImpl implements UserService {
  private final UserRepository authUserRepo;

  @Override
  public User getCurrentUserDetail() {
    final String userPkId = UserContext.getUserPkId();
    return authUserRepo
        .findByPkId(userPkId)
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  @Override
  public User getUserById(String id) {
    return authUserRepo.findById(id).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  @Override
  public List<User> getUserByIds(Iterable<String> ids) {
    return authUserRepo.findByIds(ids);
  }
}
