// package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

// import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_TYPE;

// import java.net.URI;
// import java.util.Set;

// import vn.io.vutiendat3601.beatbuddy.api.client.auth.resource.type.ScopePermisison;
// import vn.io.vutiendat3601.beatbuddy.api.common.type.UriResource;

// public record PlaylistUriResource(
//     Playlist playlist,
//     Set<URI> uris,
//     Set<String> scopes,
//     String ownerId,
//     Set<ScopePermisison> permissions)
//     implements UriResource {

//   @Override
//   public String urn() {
//     return playlist.urn();
//   }

//   @Override
//   public String name() {
//     return playlist.name();
//   }

//   @Override
//   public String type() {
//     return PLAYLIST_TYPE;
//   }
// }
