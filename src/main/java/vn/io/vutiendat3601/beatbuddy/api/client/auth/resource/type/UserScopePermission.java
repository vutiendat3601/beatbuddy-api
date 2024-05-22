package vn.io.vutiendat3601.beatbuddy.api.client.auth.resource.type;

public record UserScopePermission(String userId, String scope, boolean isGranted)
    implements ScopePermisison {
  @Override
  public String requester() {
    return userId;
  }
}
