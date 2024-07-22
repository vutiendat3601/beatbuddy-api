package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.adapter;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.ResourceConstant.RESOURCE_OWNER_ID_ATTRIBUTE;

import java.util.HashMap;
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
import org.springframework.stereotype.Repository;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak.ResourceManagementConfig;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak.UserManagementConfig;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ResourceUser;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ScopePermission;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.ResourceUtils;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.UserUtils;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.ResourceRepository;

@Repository
public class ResourceKeycloakRepositoryAdapter implements ResourceRepository {
  private final String realm;
  private final Keycloak keycloak;
  private final AuthzClient authzClient;

  public ResourceKeycloakRepositoryAdapter(
      ResourceManagementConfig resourceManagementConfig,
      UserManagementConfig userManagementConfig) {
    authzClient = resourceManagementConfig.createClient();
    realm = userManagementConfig.getRealm();
    keycloak = userManagementConfig.createClient();
  }

  @Override
  public Optional<Resource> findByUrn(String urn) {
    final ResourceRepresentation resourceRep = authzClient.protection().resource().findByName(urn);
    if (resourceRep != null) {
      final String ownerId = ResourceUtils.getOwnerId(resourceRep);
      final List<UserRepresentation> users =
          keycloak.realm(realm).users().searchByAttributes("id:" + ownerId);
      if (!users.isEmpty()) {
        final UserRepresentation userRep = users.get(0);
        final ResourceUser owner =
            ResourceUser.builder()
                .id(UserUtils.getId(userRep))
                .urn(UserUtils.getUrn(userRep))
                .firstName(userRep.getFirstName())
                .lastName(userRep.getLastName())
                .picture(UserUtils.getPicture(userRep))
                .build();
        final Set<ScopePermission> scopePermissions = getScopePermissions(resourceRep);
        final Resource resource =
            Resource.builder()
                .pkId(resourceRep.getId())
                .urn(resourceRep.getName())
                .name(resourceRep.getName())
                .uris(resourceRep.getUris())
                .scopes(
                    resourceRep.getScopes().stream()
                        .map(s -> s.getName())
                        .collect(Collectors.toSet()))
                .owner(owner)
                .scopePermissions(scopePermissions)
                .type(resourceRep.getType())
                .build();
        return Optional.of(resource);
      }
    }
    return Optional.empty();
  }

  private Set<ScopePermission> getScopePermissions(ResourceRepresentation resourceRep) {
    final Map<String, String> availableScopesMap =
        resourceRep.getScopes().stream()
            .collect(Collectors.toMap(s -> s.getId(), s -> s.getName()));
    final List<PermissionTicketRepresentation> permissionReps =
        authzClient.protection().permission().findByResource(resourceRep.getId());
    final Map<String, ResourceUser> usersMap = new HashMap<>();
    return permissionReps.stream()
        .filter(p -> availableScopesMap.containsKey(p.getScope()))
        .map(
            p -> {
              final String userPkId = p.getRequester();
              if (!usersMap.containsKey(userPkId)) {
                final UserRepresentation userRep =
                    keycloak.realm(realm).users().get(userPkId).toRepresentation();
                usersMap.put(
                    userPkId,
                    ResourceUser.builder()
                        .id(UserUtils.getId(userRep))
                        .urn(UserUtils.getUrn(userRep))
                        .firstName(userRep.getFirstName())
                        .lastName(userRep.getLastName())
                        .picture(UserUtils.getPicture(userRep))
                        .build());
              }
              return ScopePermission.builder()
                  .isGranted(p.isGranted())
                  .user(usersMap.get(userPkId))
                  .scope(availableScopesMap.get(p.getScope()))
                  .build();
            })
        .collect(Collectors.toSet());
  }

  private List<PermissionTicketRepresentation> getPermissionRepresentations(String resourceId) {
    return authzClient.protection().permission().findByResource(resourceId);
  }

  private void saveScopePermissions(
      ResourceRepresentation resourceRep, Set<ScopePermission> scopePermissions) {
    final Set<String> availableScopes =
        resourceRep.getScopes().stream().map(s -> s.getName()).collect(Collectors.toSet());
    final List<PermissionTicketRepresentation> permissionReps =
        getPermissionRepresentations(resourceRep.getId());
    final Map<String, UserRepresentation> usersMap = new HashMap<>();

    for (ScopePermission scopePermission : scopePermissions) {
      if (availableScopes.contains(scopePermission.getScope())) {
        final String scope = scopePermission.getScope();
        final String userId = scopePermission.getUser().getId();
        if (!usersMap.containsKey(userId)) {
          final List<UserRepresentation> users =
              keycloak.realm(realm).users().searchByAttributes("id:" + userId);
          if (!users.isEmpty()) {
            final UserRepresentation userRep = users.get(0);
            usersMap.put(userId, userRep);
          }
        }
        if (usersMap.containsKey(userId)) {
          final UserRepresentation userRep = usersMap.get(userId);
          final String userPkId = userRep.getId();
          permissionReps.stream()
              .filter(p -> p.getRequester().equals(userPkId) && p.getScopeName().equals(scope))
              .findFirst()
              .ifPresentOrElse(
                  p -> {
                    // If Permission is in the list and new isGranted is set to false then delete
                    // the
                    // permission ticket
                    if (!scopePermission.getIsGranted()) {
                      authzClient.protection().permission().delete(p.getId());
                    }
                  },
                  () -> {
                    final PermissionTicketRepresentation permissionRep =
                        ResourceUtils.createPermissionTicketRepresentation(scopePermission);
                    permissionRep.setResource(resourceRep.getId());
                    permissionRep.setRequester(userPkId);
                    authzClient.protection().permission().create(permissionRep);
                  });
        }
      }
    }
  }

  @Override
  public void save(Resource resource) {
    final List<UserRepresentation> users =
        keycloak.realm(realm).users().searchByAttributes("id:" + resource.getOwner().getId());
    if (!users.isEmpty()) {
      final UserRepresentation owner = users.get(0);
      ResourceRepresentation resourceRep =
          authzClient.protection().resource().findByName(resource.getUrn());
      if (resourceRep == null) {
        resourceRep = ResourceUtils.createResourceRepresentation(resource);
        resourceRep.setAttributes(
            Map.of(RESOURCE_OWNER_ID_ATTRIBUTE, List.of(UserUtils.getId(owner))));
        resourceRep = authzClient.protection().resource().create(resourceRep);
      } else {
        resourceRep.setDisplayName(resource.getName());
        resourceRep.setUris(resource.getUris());
        resourceRep.setScopes(
            resource.getScopes().stream()
                .map(ScopeRepresentation::new)
                .collect(Collectors.toSet()));
        authzClient.protection().resource().update(resourceRep);
      }
      saveScopePermissions(resourceRep, resource.getScopePermissions());
    }
  }
}
