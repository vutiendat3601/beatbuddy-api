package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_ID_LENGTH;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.UserConstant.USER_ID_LENGTH;

import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ArtistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.LikeDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.SearchDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.TrackDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.Catalog;

@SecurityRequirement(name = "web")
@Tag(name = "Catalog")
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/catalog")
public class CatalogController {
  private final Catalog catalog;
  private final CatalogPresenter catalogPresenter;

  /* #: Catalog */

  @Operation(summary = "Get Popular Artists", description = "Get popular Artists")
  @GetMapping("feed/popular-artists")
  public ResponseEntity<Pagination<ArtistDto>> getPopularArtists(
      @Min(value = 1, message = "page must be greater than or equal to 1") @RequestParam(required = false, defaultValue = "1") Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]") @RequestParam(required = false, defaultValue = "10") Integer size) {
    return catalogPresenter.presentArtistPage(catalog.getPopularArtists(page - 1, size));
  }

  @Operation(summary = "Get Popular Artists", description = "Get popular Artists")
  @GetMapping("feed/popular-tracks")
  public ResponseEntity<Pagination<TrackDto>> getPopularTracks(
      @Min(value = 1, message = "page must be greater than or equal to 1") @RequestParam(required = false, defaultValue = "1") Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]") @RequestParam(required = false, defaultValue = "10") Integer size) {
    return catalogPresenter.presentTrackPage(catalog.getPopularTracks(page - 1, size));
  }

  @Operation(summary = "Search", description = "Search catalog items")
  @GetMapping("search")
  public ResponseEntity<SearchDto> search(
      @NotBlank(message = "query must not be blank") @RequestParam String query,
      @RequestParam(required = false, defaultValue = "track,artist") List<@Pattern(regexp = "^(track|artist)$", message = "types must be in [track, artist]") String> types,
      @Min(value = 1, message = "page must be greater than or equal to 1") @RequestParam(required = false, defaultValue = "1") Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]") @RequestParam(required = false, defaultValue = "10") Integer size) {
    final Set<String> typesSet = Set.copyOf(types);
    final Set<Pagination<?>> results = catalog.search(query, typesSet, page - 1, size);
    return catalogPresenter.presentSearch(results, typesSet, page, size);
  }

  @Operation(summary = "Get User Like Detail", description = "Get current User's Like in detail")
  @GetMapping("me/like")
  public ResponseEntity<LikeDto> getCurrentUserLikeDetail() {
    return catalogPresenter.presentLike(catalog.getCurrentUserLikeDetail());
  }

  /* # Catalog */

  /* #: User */
  @Tag(name = "User")
  @Operation(summary = "Get User", description = "Get a User by id")
  @GetMapping(path = "users/{id}")
  public ResponseEntity<UserDto> getUser(
      @Length(min = USER_ID_LENGTH, max = USER_ID_LENGTH, message = "Wrong id format") @PathVariable String id) {
    return catalogPresenter.presentUser(catalog.getUserById(id));
  }

  /* # User */

  /* #: Playlist */
  @Tag(name = "Playlist")
  @Operation(summary = "Get Playlist", description = "Get a Playlist by id")
  @GetMapping(path = "playlists/{id}")
  public ResponseEntity<PlaylistDto> getPlaylist(
      @Length(min = PLAYLIST_ID_LENGTH, max = PLAYLIST_ID_LENGTH, message = "Wrong id format") @PathVariable String id) {
    return catalogPresenter.presentPlaylist(catalog.getPublicPlaylistById(id));
  }
  /* # Playlist */
}
