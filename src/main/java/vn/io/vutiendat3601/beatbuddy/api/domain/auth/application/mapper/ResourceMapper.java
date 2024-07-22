package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.mapper;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.ResourceDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;

public interface ResourceMapper {
  static ResourceDto mapToResourceDto(Resource resource) {
    return ResourceDto.builder()
        .urn(resource.getUrn())
        .name(resource.getName())
        .type(resource.getType())
        .owner(resource.getOwner())
        .uris(resource.getUris())
        .scopes(resource.getScopes())
        .scopePermissions(resource.getScopePermissions())
        .build();
  }
}
