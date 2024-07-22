package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper;

import java.util.HashSet;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Like;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.LikePo;

public interface LikeMapper {
  static Like mapToLike(LikePo likePo) {
    return new Like(likePo.getPkId(), new HashSet<>(likePo.getUrns()), likePo.getOwnerId());
  }
}
