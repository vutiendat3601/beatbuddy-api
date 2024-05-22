package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.facade;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_NOT_FOUND;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.AuthResourceConfig;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.exception.UserNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.AuthResource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.outgoing.AuthUserRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource.ResourceType;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource.ScopePermission;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.AuthResourceUtil;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.AuthResourceRepository;
import vn.io.vutiendat3601.beatbuddy.api.util.UserContext;

@Service
public class AuthResourceFacade implements AuthResource {
  private final AuthResourceRepository resourceRepo;
  private final AuthUserRepository userRepo;
  private final AuthResourceUtil authResourceUtil;
  private final Map<String, ResourceType> resourceTypes;

  public AuthResourceFacade(
      AuthResourceRepository resourceRepo,
      AuthUserRepository userRepo,
      AuthResourceConfig authResourceConfig) {
    this.resourceRepo = resourceRepo;
    this.userRepo = userRepo;
    this.authResourceUtil = new AuthResourceUtil(authResourceConfig.getUrn());
    resourceTypes =
        authResourceConfig.getTypes().stream()
            .collect(Collectors.toMap(ResourceType::getName, t -> t));
  }

  @Override
  public void createResource(String urn, String name) {
    if (!authResourceUtil.isResourceUrn(urn)) {
      throw new IllegalArgumentException("Invalid Resource URN: " + urn);
    }
    final String type = authResourceUtil.getResourceType(urn);
    final String id = authResourceUtil.getResourceId(urn);
    if (resourceTypes.containsKey(type)) {
      final User owner =
          userRepo
              .findByPkId(UserContext.getUserId())
              .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
      final ResourceType resourceType = resourceTypes.get(type);
      final Set<URI> uris =
          resourceType.getUriPrefixes().stream()
              .map(prefix -> URI.create(prefix + "/" + id))
              .collect(Collectors.toSet());
      final Set<ScopePermission> permissions =
          resourceType.getOwnerScopes().stream()
              .map(scope -> new ScopePermission(owner.urn(), scope, true))
              .collect(Collectors.toSet());
      final Resource resource =
          new Resource(urn, name, type, owner, uris, resourceType.getOwnerScopes(), permissions);
      resourceRepo.create(resource);
    }
  }
}
