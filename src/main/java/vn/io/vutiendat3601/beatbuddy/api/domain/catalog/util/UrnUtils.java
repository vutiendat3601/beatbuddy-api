package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.util;

import static vn.io.vutiendat3601.beatbuddy.api.common.constant.GlobalConstant.RESOURCE_TYPES;

public interface UrnUtils {
  static String identifyType(String urn) {
    if (urn != null) {
      for (String urnPrefix : RESOURCE_TYPES.keySet()) {
        if (urn.startsWith(urnPrefix)) {
          return RESOURCE_TYPES.get(urnPrefix);
        }
      }
    }
    return null;
  }
}
