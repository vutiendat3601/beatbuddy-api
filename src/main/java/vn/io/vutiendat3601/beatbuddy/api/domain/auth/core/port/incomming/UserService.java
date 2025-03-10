package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming;

import java.util.List;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;

public interface UserService {
  User getCurrentUserDetail();

  User getUserById(String id);

  List<User> getUserByIds(Iterable<String> urns);
}
