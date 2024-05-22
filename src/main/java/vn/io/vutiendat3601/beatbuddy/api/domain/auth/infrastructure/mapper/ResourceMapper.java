package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.mapper;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import org.keycloak.representations.idm.authorization.PermissionTicketRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.model.ResourcePo;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource.ScopePermission;

public interface ResourceMapper {
  static Resource mapToResource(ResourcePo resourcePo) {
    final ResourceRepresentation resourceRep = resourcePo.getResourceRepresentation();
    final Set<ScopePermission> permissions =
        resourcePo.getPermissions().stream()
            .map(
                spPo -> {
                  final String userId = spPo.getUserId();
                  final PermissionTicketRepresentation permissionRep =
                      spPo.getPermissionTicketRepresentation();
                  return new ScopePermission(
                      userId, permissionRep.getScopeName(), permissionRep.isGranted());
                })
            .collect(Collectors.toSet());
    return new Resource(
        resourceRep.getName(),
        resourceRep.getDisplayName(),
        resourceRep.getType(),
        UserMapper.mapToUser(resourcePo.getOwner()),
        resourceRep.getUris().stream().map(URI::create).collect(Collectors.toSet()),
        resourceRep.getScopes().stream()
            .map(ScopeRepresentation::getName)
            .collect(Collectors.toSet()),
        permissions);
  }
}
