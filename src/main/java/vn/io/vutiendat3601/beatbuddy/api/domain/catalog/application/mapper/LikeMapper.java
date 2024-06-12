package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.LikeDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Like;

public interface LikeMapper {
  static LikeDto mapToLikeDto(Like like) {
    return LikeDto.builder().ownerId(like.getOwnerId()).urns(like.getUrns()).build();
  }
}
