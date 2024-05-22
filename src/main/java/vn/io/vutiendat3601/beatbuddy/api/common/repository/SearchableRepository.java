package vn.io.vutiendat3601.beatbuddy.api.common.repository;

import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.common.type.SearchRequest;

public interface SearchableRepository<T> {
  Pagination<T> findBySearchRequest(SearchRequest searchReq);
}
