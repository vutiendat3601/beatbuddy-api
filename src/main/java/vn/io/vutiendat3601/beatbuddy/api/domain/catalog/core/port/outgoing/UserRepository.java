package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing;

import java.util.Optional;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

public interface UserRepository {
  Optional<User> findById(String id);

  Optional<User> findCurrentUser();
}
