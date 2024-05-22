package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing;

import java.util.List;
import java.util.Optional;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;

public interface AuthResourceRepository {
  Optional<Resource> findByUrn(String urn);

  void create(Resource resource);

  List<Resource> findByUrns(Iterable<String> urns);
}
