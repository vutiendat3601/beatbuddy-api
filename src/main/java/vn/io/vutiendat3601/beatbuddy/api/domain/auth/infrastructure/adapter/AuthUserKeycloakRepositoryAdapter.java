package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.adapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.outgoing.AuthUserRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.mapper.UserMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.repository.AuthUserKeycloakRepository;

@RequiredArgsConstructor
@Repository
public class AuthUserKeycloakRepositoryAdapter implements AuthUserRepository {
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
  public List<User> findByUrns(Iterable<String> urns) {
    final List<User> users = new LinkedList<>();
    for (String urn : urns) {
      userResourceKeycloakRepo
          .findByQuery("urn:" + urn)
          .ifPresent(
              userPo -> {
                users.add(UserMapper.mapToUser(userPo));
              });
    }
    return users;
  }
}
