package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant;

public interface PlaylistConstant {
  int PLAYLIST_ID_LENGTH = 16;
  String PLAYLIST_TYPE = "playlist";
  String PLAYLIST_URI_PREFIX = "/v1/catalog/playlists";
  String PLAYLIST_URN_PREFIX = "beatbuddy:playlist";

  // Messages
  String PLAYLIST_CREATED_SUCCESS = "Playlist created successfully";
  String PLAYLIST_UPDATED_SUCCESS = "Playlist updated successfully";
  String PLAYLIST_PERMISISON_GRANTED_SUCCESS = "Resource Permission granted successfully";
  String PLAYLIST_PERMISISON_REVOKED_SUCCESS = "Resource Permission revoked successfully";
  String PLAYLIST_NOT_FOUND = "Playlist not found";

  // Scopes
  // String PLAYLIST_SCOPE_VIEW = "playlist:view";
  // String PLAYLIST_SCOPE_EDIT = "playlist:edit";
  // String PLAYLIST_SCOPE_DELETE = "playlist:delete";
  // String PLAYLIST_SCOPE_PERMISSION = "playlist:permission";
  // String[] PLAYLIST_OWNER_SCOPES = {
  //   PLAYLIST_SCOPE_PERMISSION, PLAYLIST_SCOPE_VIEW, PLAYLIST_SCOPE_EDIT, PLAYLIST_SCOPE_DELETE
  // };
}
