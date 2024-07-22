package vn.io.vutiendat3601.beatbuddy.api.domain.auth.util;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.ResourceConstant.RESOURCE_OWNER_ID_ATTRIBUTE;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.keycloak.representations.idm.authorization.PermissionTicketRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ScopePermission;

public interface ResourceUtils {
  static String getOwnerId(ResourceRepresentation resourceRep) {
    final Map<String, List<String>> attrs = resourceRep.getAttributes();
    if (attrs.containsKey(RESOURCE_OWNER_ID_ATTRIBUTE)) {
      final List<String> attrValues = attrs.get(RESOURCE_OWNER_ID_ATTRIBUTE);
      return attrValues.isEmpty() ? null : attrValues.get(0);
    }
    return null;
  }

  static Map<String, ScopeRepresentation> getScopesMap(ResourceRepresentation resourceRep) {
    return resourceRep.getScopes().stream()
        .collect(Collectors.toMap(ScopeRepresentation::getName, scope -> scope));
  }

  static ResourceRepresentation createResourceRepresentation(Resource resource) {
    final Set<String> uris = resource.getUris();
    final Set<ScopeRepresentation> scopes =
        resource.getScopes().stream().map(ScopeRepresentation::new).collect(Collectors.toSet());
    final ResourceRepresentation resourceRep = new ResourceRepresentation();
    resourceRep.setOwnerManagedAccess(true);
    resourceRep.setName(resource.getUrn());
    resourceRep.setDisplayName(resource.getName());
    resourceRep.setType(resource.getType());
    resourceRep.setUris(uris);
    resourceRep.setScopes(scopes);
    // Set resource ownerId attribute
    final Map<String, List<String>> attrs =
        Map.of(RESOURCE_OWNER_ID_ATTRIBUTE, List.of(resource.getOwner().getId()));
    resourceRep.setAttributes(attrs);
    return resourceRep;
  }

  static PermissionTicketRepresentation createPermissionTicketRepresentation(
      ScopePermission scopePermisison) {
    final PermissionTicketRepresentation permissionRep = new PermissionTicketRepresentation();
    permissionRep.setScopeName(scopePermisison.getScope());
    permissionRep.setGranted(scopePermisison.getIsGranted());
    return permissionRep;
  }
}
