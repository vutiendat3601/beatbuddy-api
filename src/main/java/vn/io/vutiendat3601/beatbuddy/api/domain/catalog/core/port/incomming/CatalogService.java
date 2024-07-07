package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming;

import java.util.Set;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Like;

public interface CatalogService {
  Set<Pagination<?>> search(String query, Set<String> types, int page, int size);

  Like getCurrentUserLikeDetail();
}
