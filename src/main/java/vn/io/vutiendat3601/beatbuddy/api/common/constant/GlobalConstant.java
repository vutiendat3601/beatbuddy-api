package vn.io.vutiendat3601.beatbuddy.api.common.constant;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_TYPE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_URN_PREFIX;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_TYPE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_URN_PREFIX;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_TYPE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_URN_PREFIX;

import java.util.Map;

public class GlobalConstant {
  public static final Map<String, String> RESOURCE_TYPES =
      Map.of(TRACK_URN_PREFIX, TRACK_TYPE, ARTIST_URN_PREFIX, ARTIST_TYPE, PLAYLIST_URN_PREFIX, PLAYLIST_TYPE);
}
