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
  private final AuthUserRepository userResourceRepo;

  @Override
  public User getCurrentUserDetail() {
    final String pkId = UserContext.getUserId();
    return userResourceRepo
        .findByPkId(pkId)
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  @Override
  public User getUserById(String id) {
    return userResourceRepo
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  @Override
  public List<User> getUserByUrns(Iterable<String> urns) {
    return userResourceRepo.findByUrns(urns);
  }
}
