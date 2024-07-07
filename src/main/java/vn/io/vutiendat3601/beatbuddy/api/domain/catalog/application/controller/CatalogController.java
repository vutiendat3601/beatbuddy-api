package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ArtistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.SearchDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.TrackDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.ArtistPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.PlaylistPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.TrackPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.ArtistService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.CatalogService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.PlaylistService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.TrackService;

@Tag(name = "Catalog")
@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RequestMapping("v1/catalog")
@RestController
public class CatalogController {
  private final PlaylistService playlistService;
  private final CatalogService catalogService;
  private final ArtistService artistService;
  private final TrackService trackService;

  private final CatalogPresenter catalogPresenter;
  private final PlaylistPresenter playlistPresenter;
  private final TrackPresenter trackPresenter;
  private final ArtistPresenter artistPresenter;

  @Operation(summary = "Get Popular Artists", description = "Get popular Artists")
  @GetMapping("feed/popular-artists")
  public ResponseEntity<Pagination<ArtistDto>> getPopularArtists(
      @Min(value = 1, message = "page must be greater than or equal to 1")
          @RequestParam(required = false, defaultValue = "1")
          Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]")
          @RequestParam(required = false, defaultValue = "10")
          Integer size) {
    return artistPresenter.presentArtistDtoPage(artistService.getPopularArtists(page - 1, size));
  }

  @Operation(summary = "Get Popular Artists", description = "Get popular Artists")
  @GetMapping("feed/popular-tracks")
  public ResponseEntity<Pagination<TrackDto>> getPopularTracks(
      @Min(value = 1, message = "page must be greater than or equal to 1")
          @RequestParam(required = false, defaultValue = "1")
          Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]")
          @RequestParam(required = false, defaultValue = "10")
          Integer size) {
    return trackPresenter.presentTrackDtoPage(trackService.getPopularTracks(page - 1, size));
  }

  @Operation(summary = "Get Popular Playlists", description = "Get popular Playlists")
  @GetMapping("feed/popular-playlists")
  public ResponseEntity<Pagination<PlaylistDto>> getPopularPlaylists(
      @Min(value = 1, message = "page must be greater than or equal to 1")
          @RequestParam(required = false, defaultValue = "1")
          Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]")
          @RequestParam(required = false, defaultValue = "10")
          Integer size) {
    return playlistPresenter.presentPlaylistDtoPage(
        playlistService.getPopularPlaylists(page - 1, size));
  }

  @Operation(summary = "Search", description = "Search catalog items")
  @GetMapping("search")
  public ResponseEntity<SearchDto> search(
      @NotBlank(message = "query must not be blank") @RequestParam String query,
      @RequestParam(required = false, defaultValue = "track,artist")
          List<
                  @Pattern(
                      regexp = "^(track|artist)$",
                      message = "types must be in [track, artist]")
                  String>
              types,
      @Min(value = 1, message = "page must be greater than or equal to 1")
          @RequestParam(required = false, defaultValue = "1")
          Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]")
          @RequestParam(required = false, defaultValue = "10")
          Integer size) {
    final Set<String> typesSet = Set.copyOf(types);
    final Set<Pagination<?>> results = catalogService.search(query, typesSet, page - 1, size);
    return catalogPresenter.presentSearchDto(results, typesSet, page, size);
  }
}
