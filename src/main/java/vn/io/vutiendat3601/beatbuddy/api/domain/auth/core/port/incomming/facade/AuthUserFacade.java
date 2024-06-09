package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.facade;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.exception.UserNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.AuthUser;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.outgoing.AuthUserRepository;
import vn.io.vutiendat3601.beatbuddy.api.util.UserContext;

@RequiredArgsConstructor
@Service
public class AuthUserFacade implements AuthUser {
  private final AuthUserRepository authUserRepo;

  @Override
  public User getCurrentUserDetail() {
    final String userPkId = UserContext.getUserPkId();
    return authUserRepo
        .findByPkId(userPkId)
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  @Override
  public User getUserById(String id) {
    return authUserRepo
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  @Override
  public List<User> getUserByIds(Iterable<String> ids) {
    return authUserRepo.findByIds(ids);
  }
}
