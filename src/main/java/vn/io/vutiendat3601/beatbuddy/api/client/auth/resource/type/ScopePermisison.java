package vn.io.vutiendat3601.beatbuddy.api.client.auth.resource.type;

public interface ScopePermisison {
  String requester();

  String scope();

  boolean isGranted();
}
