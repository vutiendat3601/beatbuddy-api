package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.facade;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.ResourceConstant.RESOURCE_NOT_FOUND;
import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_NOT_FOUND;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.AuthResourceConfig;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.exception.UserNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.AuthResource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.outgoing.AuthUserRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ResourceType;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ResourceUser;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ScopePermission;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.AuthResourceUtil;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.ResourceNotFoundException;
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
    final String type = authResourceUtil.getResourceType(urn);
    final String id = authResourceUtil.getResourceId(urn);
    if (resourceTypes.containsKey(type)) {
      final User user =
          userRepo
              .findById(UserContext.getUserId())
              .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
      final ResourceUser owner =
          ResourceUser.builder()
              .id(user.getId())
              .firstName(user.getFirstName())
              .lastName(user.getLastName())
              .picture(user.getPicture())
              .build();

      final ResourceType resourceType = resourceTypes.get(type);
      final Set<String> uris =
          resourceType.getUriPrefixes().stream()
              .map(prefix -> UriComponentsBuilder.fromPath(prefix).buildAndExpand(id).toString())
              .collect(Collectors.toSet());
      final Set<ScopePermission> permissions =
          resourceType.getOwnerScopes().stream()
              .map(s -> new ScopePermission(s, true, owner))
              .collect(Collectors.toSet());
      final Resource resource =
          Resource.builder()
              .urn(urn)
              .name(name)
              .type(type)
              .owner(owner)
              .uris(uris)
              .scopes(resourceType.getOwnerScopes())
              .scopePermissions(permissions)
              .build();
      resourceRepo.save(resource);
    }
  }

  public Resource getResource(String urn) {
    return resourceRepo
        .findByUrn(urn)
        .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
  }
}
