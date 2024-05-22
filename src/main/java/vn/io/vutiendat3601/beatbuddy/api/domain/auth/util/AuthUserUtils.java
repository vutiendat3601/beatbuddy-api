package vn.io.vutiendat3601.beatbuddy.api.domain.auth.util;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_ID_ATTRIBUTE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_ID_LENGTH;
import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_PICTURE_ATTRIBUTE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_URN_ATTRIBUTE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_URN_PREFIX;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.keycloak.representations.idm.UserRepresentation;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.User;
import vn.io.vutiendat3601.beatbuddy.api.util.StringUtils;

public interface AuthUserUtils {
  static String getId(UserRepresentation userRep) {
    final Map<String, List<String>> attrs = userRep.getAttributes();
    if (attrs.containsKey(USER_ID_ATTRIBUTE)) {
      final List<String> attrValues = attrs.get(USER_ID_ATTRIBUTE);
      return attrValues.isEmpty() ? null : attrValues.get(0);
    }
    return null;
  }

  static String getUrn(UserRepresentation userRep) {
    final Map<String, List<String>> attrs = userRep.getAttributes();
    if (attrs.containsKey(USER_URN_ATTRIBUTE)) {
      final List<String> attrValues = attrs.get(USER_URN_ATTRIBUTE);
      return attrValues.isEmpty() ? null : attrValues.get(0);
    }
    return null;
  }

  static UserRepresentation generateRandomId(UserRepresentation userRep) {
    if (getId(userRep) == null) {
      final Map<String, List<String>> attrs = new HashMap<>();
      final String id = StringUtils.makeRandomString(USER_ID_LENGTH);
      attrs.put(USER_ID_ATTRIBUTE, List.of(id));
      attrs.put(USER_URN_ATTRIBUTE, List.of(USER_URN_PREFIX + ":" + id));
      userRep.setAttributes(attrs);
      userRep.setAttributes(attrs);
    }
    return userRep;
  }

  static UserRepresentation createUserRepresentation(Map<String, List<String>> attributes) {
    final UserRepresentation userRep = new UserRepresentation();
    userRep.setAttributes(attributes);
    return userRep;
  }

  static UserRepresentation createUserRepresentation(User user) {
    UserRepresentation userRep = new UserRepresentation();
    userRep.setId(user.pkId());
    userRep.setFirstName(user.firstName());
    userRep.setLastName(user.lastName());
    userRep.setUsername(user.username());
    userRep.setEmail(user.email());
    userRep.setEmailVerified(user.isEmailVerified());

    final Map<String, List<String>> attributes = new HashMap<>();
    attributes.put(USER_ID_ATTRIBUTE, List.of(user.id()));
    attributes.put(USER_URN_ATTRIBUTE, List.of(user.urn()));
    attributes.put(USER_PICTURE_ATTRIBUTE, List.of(user.picture()));
    userRep.setAttributes(attributes);
    return userRep;
  }
}
