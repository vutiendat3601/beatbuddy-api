package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.repository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.PermissionTicketRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak.ResourceManagementConfig;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak.UserManagementConfig;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.model.ResourcePo;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.model.ScopePermissionPo;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.ResourceUtils;

// @Repository
public class AuthResourceKeycloakRepository {
  private final String realm;
  private final Keycloak keycloak;
  private final AuthzClient authzClient;

  public AuthResourceKeycloakRepository(
      ResourceManagementConfig resourceManagementConfig,
      UserManagementConfig userManagementConfig) {
    authzClient = resourceManagementConfig.createClient();
    realm = userManagementConfig.getRealm();
    keycloak = userManagementConfig.createClient();
  }

  public Optional<ResourcePo> findByName(String name) {
    // Find resource
    final ResourceRepresentation resourceRep = authzClient.protection().resource().findByName(name);
    if (resourceRep != null) {
      final String ownerId = ResourceUtils.getOwnerId(resourceRep);
      final UserRepresentation owner =
          findUserById(ownerId).orElse(findUserByPkId(resourceRep.getOwner().getId()));

      // Find permissions
      final List<PermissionTicketRepresentation> permissionReps =
          authzClient.protection().permission().findByResource(resourceRep.getId());
      final Set<ScopePermissionPo> permissions =
          permissionReps.stream()
              .map(
                  p -> {
                    // Find User Public ID of permissions, requester existed
                    final UserRepresentation userRep = findUserByPkId(p.getRequester());
                    return new ScopePermissionPo(p, userRep.getId());
                  })
              .filter(p -> p.getUserId() != null)
              .collect(Collectors.toSet());
      return Optional.of(new ResourcePo(resourceRep, owner, permissions));
    }
    return Optional.empty();
  }

  public List<ResourcePo> findByNameIn(Iterable<String> names) {
    final List<ResourcePo> resourcePos = new LinkedList<>();
    for (String name : names) {
      findByName(name).ifPresent(resourcePos::add);
    }
    return resourcePos;
  }

  public void save(ResourcePo resourcePo) {
    // Create resource
    ResourceRepresentation resourceRep =
        authzClient.protection().resource().create(resourcePo.getResourceRepresentation());
    // final String resourceId = resourceRep.getId();

    // // List all resource scopes
    Set<PermissionTicketRepresentation> permissionReps =
        resourceRep.getScopes().stream()
            .map(
                scopeRep -> {
                  final PermissionTicketRepresentation permissionRep =
                      new PermissionTicketRepresentation();
                  permissionRep.setScopeName(scopeRep.getName());
                  permissionRep.setResource(resourceRep.getId());
                  permissionRep.setRequester(resourcePo.getOwner().getId());
                  permissionRep.setGranted(true);
                  return permissionRep;
                })
            .collect(Collectors.toSet());
    createPermissions(permissionReps);
  }

  public void updateResource(ResourcePo resourcePo) {
    final ResourceRepresentation updateResourceRep = resourcePo.getResourceRepresentation();
    final ResourceRepresentation resourceRep =
        authzClient.protection().resource().findByName(updateResourceRep.getName());
    if (resourceRep != null) {
      resourceRep.setDisplayName(updateResourceRep.getDisplayName());
      authzClient.protection().resource().update(resourceRep);

      final Map<String, ScopeRepresentation> resourceScopesMap =
          ResourceUtils.getScopesMap(resourceRep);

      // // Save permissions
      final String resourceId = resourceRep.getId();
      final Set<PermissionTicketRepresentation> createPermissionReps = new HashSet<>();
      final Set<PermissionTicketRepresentation> updatePermissionReps = new HashSet<>();

      for (ScopePermissionPo scopePermissionPo : resourcePo.getPermissions()) {
        final PermissionTicketRepresentation permissionRep =
            scopePermissionPo.getPermissionTicketRepresentation();
        if (resourceScopesMap.containsKey(permissionRep.getScopeName())) {
          // Find user in permission
          final String userId = scopePermissionPo.getUserId();
          findUserById(userId)
              .ifPresent(
                  // Found user
                  userRep -> {
                    permissionRep.setResource(resourceId);
                    // Check available scope in resource
                    findPermission(
                            resourceId,
                            resourceScopesMap.get(permissionRep.getScopeName()).getId(),
                            userRep.getId())
                        .ifPresentOrElse(
                            permission -> {
                              // Only save permission when isGranted is different
                              if (permissionRep.isGranted() != permission.isGranted()) {
                                updatePermissionReps.add(permissionRep);
                              }
                            },
                            () -> createPermissionReps.add(permissionRep));
                  });
        }
      }
      createPermissions(createPermissionReps);
      updatePermissions(updatePermissionReps);
    }
  }

  /* #: Helper methods */
  private UserRepresentation findUserByPkId(String pkId) {
    return keycloak.realm(realm).users().get(pkId).toRepresentation();
  }

  private Optional<UserRepresentation> findUserById(String id) {
    final List<UserRepresentation> userReps =
        keycloak.realm(realm).users().searchByAttributes("id:" + id);
    return userReps.isEmpty() ? Optional.empty() : Optional.of(userReps.get(0));
  }

  private Optional<PermissionTicketRepresentation> findPermission(
      String resourceId, String scopeId, String userPkId) {
    final List<PermissionTicketRepresentation> permissions =
        authzClient
            .protection()
            .permission()
            .find(resourceId, scopeId, null, userPkId, null, null, null, null);
    return permissions.isEmpty() ? Optional.empty() : Optional.of(permissions.get(0));
  }

  private void createPermissions(Set<PermissionTicketRepresentation> permissionReps) {
    for (PermissionTicketRepresentation permissionRep : permissionReps) {
      authzClient.protection().permission().create(permissionRep);
    }
  }

  private void updatePermissions(Set<PermissionTicketRepresentation> permissionReps) {
    for (PermissionTicketRepresentation permissionRep : permissionReps) {
      authzClient.protection().permission().update(permissionRep);
    }
  }
  /* # Helper methods */
}
