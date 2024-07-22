package vn.io.vutiendat3601.beatbuddy.api.domain.auth.util;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.ResourceUrn;

public class AuthResourceUtil {
  private final int urnNumberOfParts;
  private final int urnTypePartIndex;
  private final int urnIdPartIndex;

  public AuthResourceUtil(ResourceUrn resourceUrn) {
    urnNumberOfParts = resourceUrn.getNumberOfParts();
    urnTypePartIndex = resourceUrn.getTypePartIndex();
    urnIdPartIndex = resourceUrn.getIdPartIndex();
  }

  public boolean isResourceUrn(String urn) {
    return urn != null && urn.split(":").length == urnNumberOfParts;
  }

  public String getResourceType(String urn) {
    if (isResourceUrn(urn)) {
      return urn.split(":")[urnTypePartIndex];
    }
    throw new IllegalArgumentException("Invalid Resource URN: " + urn);
  }

  public String getResourceId(String urn) {
    if (isResourceUrn(urn)) {
      return urn.split(":")[urnIdPartIndex];
    }
    throw new IllegalArgumentException("Invalid Resource URN: " + urn);
  }
}
