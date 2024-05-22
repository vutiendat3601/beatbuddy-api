package vn.io.vutiendat3601.beatbuddy.api.util;

import static vn.io.vutiendat3601.beatbuddy.api.common.constant.GlobalConstant.RESOURCE_TYPES;

import java.util.Optional;

public interface UrnUtils {
  static Optional<String> identifyType(String urn) {
    if (urn != null) {
      for (String urnPrefix : RESOURCE_TYPES.keySet()) {
        if (urn.startsWith(urnPrefix)) {
          return Optional.of(RESOURCE_TYPES.get(urnPrefix));
        }
      }
    }
    return Optional.empty();
  }
}
