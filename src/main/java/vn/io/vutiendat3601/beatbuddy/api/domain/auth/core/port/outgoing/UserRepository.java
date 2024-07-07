package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.outgoing;

import java.util.List;
import java.util.Optional;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;

public interface UserRepository {
  Optional<User> findByPkId(String pkId);

  Optional<User> findById(String id);

  List<User> findByIds(Iterable<String> ids);
}
