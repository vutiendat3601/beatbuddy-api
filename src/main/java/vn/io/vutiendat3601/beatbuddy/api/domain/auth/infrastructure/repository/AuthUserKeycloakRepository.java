package vn.io.vutiendat3601.beatbuddy.api.domain.auth.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Repository;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.config.client.keycloak.UserManagementConfig;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.util.AuthUserUtils;

@Repository
public class AuthUserKeycloakRepository {
  private final String realm;
  private final Keycloak keycloak;

  public AuthUserKeycloakRepository(UserManagementConfig userManagementConfig) {
    realm = userManagementConfig.getRealm();
    keycloak = userManagementConfig.createClient();
  }

  public Optional<UserRepresentation> findByQuery(String query) {
    final List<UserRepresentation> userReps =
        keycloak.realm(realm).users().searchByAttributes(query);
    return userReps.isEmpty() ? Optional.empty() : Optional.of(userReps.get(0));
  }

  public Optional<UserRepresentation> findByPkId(String pkId) {
    final UserResource userResource = keycloak.realm(realm).users().get(pkId);
    if (userResource == null) {
      return Optional.empty();
    }
    final UserRepresentation userRep =
        detectAndFixMissingAttributes(userResource.toRepresentation());
    return Optional.of(userRep);
  }

  private UserRepresentation detectAndFixMissingAttributes(UserRepresentation userRep) {
    if (AuthUserUtils.getId(userRep) == null) {
      userRep = AuthUserUtils.generateRandomId(userRep);
      keycloak.realm(realm).users().get(userRep.getId()).update(userRep);
    }
    return userRep;
  }

  // public static void main(String[] args) {
  //   Keycloak kc =
  //       KeycloakBuilder.builder()
  //           .serverUrl("https://auth.beatbuddy.io.vn")
  //           .realm("beatbuddy")
  //           .clientId("user-management")
  //           .clientSecret("oH6fyIexWj8OrRp9d8LPdyDgTtXc1Tb6")
  //           .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
  //           .build();
  //   // kc.realm("beatbuddy").users().searchByAttributes("urn:beatbuddy:users:1");
  //   final List<UserRepresentation> users =
  //       kc.realm("beatbuddy")
  //           .users()
  //           .searchByAttributes("beatbuddy:user:MRMJlRPfESxF2a3x
  // beatbuddy:user:Hg8n9IiiZIXpGOx8");
  //   System.out.println(users.isEmpty());
  // }
}
