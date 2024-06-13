package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.keycloak.representations.idm.UserRepresentation;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.IdentityProvider;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.AuthUserUtils;

public interface UserMapper {
  static User mapToUser(UserRepresentation userRep) {
    final String id = AuthUserUtils.getId(userRep);
    final String urn = AuthUserUtils.getUrn(userRep);
    final String picture = AuthUserUtils.getPicture(userRep);
    final Set<IdentityProvider> linkedIdentityProviders =
        Optional.ofNullable(userRep.getFederatedIdentities()).orElse(List.of()).stream()
            .map(
                fi ->
                    new IdentityProvider(
                        fi.getIdentityProvider(), fi.getUserId(), fi.getUserName()))
            .collect(Collectors.toSet());
    return new User(
        userRep.getId(),
        id,
        urn,
        userRep.getFirstName(),
        userRep.getLastName(),
        userRep.getUsername(),
        userRep.getEmail(),
        userRep.isEmailVerified(),
        picture,
        linkedIdentityProviders);
  }
}
