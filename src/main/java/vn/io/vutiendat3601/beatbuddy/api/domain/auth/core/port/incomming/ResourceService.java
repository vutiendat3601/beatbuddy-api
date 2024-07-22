package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;

public interface ResourceService {
  void createResource(String urn, String name);

  Resource getResource(String urn);
}
