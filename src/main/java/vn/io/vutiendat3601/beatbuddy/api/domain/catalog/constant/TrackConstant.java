package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant;

public interface TrackConstant {
  int TRACK_ID_LENGTH = 16;

  String TRACK_TYPE = "track";

  String TRACK_URN_PREFIX = "beatbuddy:track";

  String TRACK_NOT_FOUND = "Track not found";

  String TRACK_URI_PREFIX = "/v1/catalog/tracks";

  String TRACK_SCOPE_VIEW = "track:view";

  String TRACK_SCOPE_EDIT = "track:edit";

  String TRACK_SCOPE_DELETE = "track:delete";

  String TRACK_SCOPE_LIKE = "track:like";

  String TRACK_SCOPE_UNLIKE = "track:unlike";
}
