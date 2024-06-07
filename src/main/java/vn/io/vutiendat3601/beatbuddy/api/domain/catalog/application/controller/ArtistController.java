package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_ID_LENGTH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ArtistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.TrackDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.Catalog;

@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/artists")
public class ArtistController {
  private final Catalog catalog;
  private final CatalogPresenter catalogPresenter;

  @Tag(name = "Artist")
  @Operation(summary = "Get Artist popular Tracks", description = "Get an Artist by id")
  @GetMapping("{id}/popular-tracks")
  public ResponseEntity<Pagination<TrackDto>> getArtistPopularTracks(
      @Length(min = ARTIST_ID_LENGTH, max = ARTIST_ID_LENGTH, message = "Wrong id format")
          @PathVariable
          String id,
      @Min(value = 1, message = "page must be greater than or equal to 1")
          @RequestParam(required = false, defaultValue = "1")
          Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]")
          @RequestParam(required = false, defaultValue = "10")
          Integer size) {
    return catalogPresenter.presentTrackPage(catalog.getArtistPopularTracks(id, page - 1, size));
  }

  @Tag(name = "Artist")
  @Operation(summary = "Get Artist", description = "Get an Artist by id")
  @GetMapping("{id}")
  public ResponseEntity<ArtistDto> getArtist(
      @Length(min = ARTIST_ID_LENGTH, max = ARTIST_ID_LENGTH, message = "Wrong id format")
          @PathVariable
          String id) {
    return catalogPresenter.presentArtist(catalog.getArtistById(id));
  }

  @Tag(name = "Artist")
  @Operation(summary = "Get Artists", description = "Get several Artists by ids")
  @GetMapping
  public ResponseEntity<List<ArtistDto>> getArtists(
      @Size(min = 1, max = 100) @RequestParam
          List<
                  @Length(
                      min = ARTIST_ID_LENGTH,
                      max = ARTIST_ID_LENGTH,
                      message = "Wrong id format")
                  String>
              ids) {
    return catalogPresenter.presentArtists(catalog.getArtistByIds(ids));
  }
}
