package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming;

import java.util.List;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;

public interface AuthUser {
  User getCurrentUserDetail();

  User getUserById(String id);

  List<User> getUserByUrns(Iterable<String> urns);
}
