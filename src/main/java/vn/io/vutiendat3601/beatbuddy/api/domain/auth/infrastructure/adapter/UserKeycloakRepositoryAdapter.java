package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.adapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.outgoing.UserRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.mapper.UserMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.repository.AuthUserKeycloakRepository;

@RequiredArgsConstructor
@Repository
public class UserKeycloakRepositoryAdapter implements UserRepository {
  private final AuthUserKeycloakRepository userResourceKeycloakRepo;

  @Override
  public Optional<User> findByPkId(String pkId) {
    return userResourceKeycloakRepo.findByPkId(pkId).map(UserMapper::mapToUser);
  }

  @Override
  public Optional<User> findById(String id) {
    return userResourceKeycloakRepo.findByQuery("id:" + id).map(UserMapper::mapToUser);
  }

  @Override
  public List<User> findByIds(Iterable<String> ids) {
    final List<User> users = new LinkedList<>();
    for (String id : ids) {
      userResourceKeycloakRepo
          .findByQuery("id:" + id)
          .ifPresent(
              userPo -> {
                users.add(UserMapper.mapToUser(userPo));
              });
    }
    return users;
  }
}
