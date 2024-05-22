package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.mapper.ResourceMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.model.ResourcePo;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.model.ScopePermissionPo;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.repository.AuthResourceKeycloakRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.AuthResourceUtils;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.AuthUserUtils;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.AuthResourceRepository;

@RequiredArgsConstructor
@Repository
public class AuthResourceKeycloakRepositoryAdapter implements AuthResourceRepository {
  private final AuthResourceKeycloakRepository resourceKeycloakRepo;

  @Override
  public Optional<Resource> findByUrn(String urn) {
    return resourceKeycloakRepo.findByName(urn).map(ResourceMapper::mapToResource);
  }

  @Override
  public void create(Resource resource) {
    // Create Keycloak Resource
    final ResourceRepresentation resourceRep =
        AuthResourceUtils.createResourceRepresentation(resource);

    final UserRepresentation owner = AuthUserUtils.createUserRepresentation(resource.owner());

    // Create Keycloak Resource Permissions for Resource Owner
    final Set<ScopePermissionPo> scopePermissionPos =
        resource.permissions().stream()
            .map(
                scopePermisison ->
                    new ScopePermissionPo(
                        AuthResourceUtils.createPermissionTicketRepresentation(scopePermisison),
                        AuthUserUtils.getId(owner)))
            .collect(Collectors.toSet());
    resourceKeycloakRepo.create(new ResourcePo(resourceRep, owner, scopePermissionPos));
  }

  @Override
  public List<Resource> findByUrns(Iterable<String> urns) {
    return resourceKeycloakRepo.findByNameIn(urns).stream()
        .map(ResourceMapper::mapToResource)
        .collect(Collectors.toList());
  }
}
