package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing;

import java.util.Optional;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;

public interface ResourceRepository {
  Optional<Resource> findByUrn(String urn);

  void save(Resource resource);
}
