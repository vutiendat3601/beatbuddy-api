package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

public interface UserService {
  User getUserById(String id);
}
