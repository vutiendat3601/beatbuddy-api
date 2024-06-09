package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_CREATED_SUCCESS;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_ID_LENGTH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.common.dto.ResponseDto;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.Catalog;

@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RequestMapping("v1/playlists")
@RestController
public class PlaylistController {
  private final Catalog catalog;
  private final CatalogPresenter catalogPresenter;

  @Tag(name = "Playlist")
  @Operation(
      summary = "Create Playlist",
      description = "Create a new Playlist",
      requestBody =
          @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)))
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ResponseDto> createPlaylist(
      @NotBlank(message = "name is required") @RequestParam String name,
      @NotNull(message = "isPublic is true or false")
          @RequestParam(required = false, defaultValue = "true")
          Boolean isPublic,
      @RequestParam(required = false) String thumbnail,
      @RequestParam(required = false) String description) {
    catalog.createPlaylist(name, isPublic, thumbnail, description);
    return catalogPresenter.presentResponseOk(PLAYLIST_CREATED_SUCCESS);
  }

  @Tag(name = "Playlist")
  @Operation(summary = "Add Items to Playlist", description = "Add Items to Playlist")
  @PutMapping(path = "{id}/add-item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ResponseDto> addTrackToPlaylist(
      @Length(min = PLAYLIST_ID_LENGTH, max = PLAYLIST_ID_LENGTH, message = "Wrong id format")
          @PathVariable
          String id,
      @NotEmpty @RequestParam List<String> itemUrns) {
    catalog.addTrackToPlaylist(id, itemUrns);
    return catalogPresenter.presentResponseOk(PLAYLIST_CREATED_SUCCESS);
  }

  @Tag(name = "Playlist")
  @Operation(
      summary = "Get Authenticated User Playlists",
      description = "Get Authenticated User Playlists")
  @GetMapping
  public ResponseEntity<Pagination<PlaylistDto>> getUserPlaylists(
      @Min(value = 1, message = "page must be greater than or equal to 1")
          @RequestParam(required = false, defaultValue = "1")
          Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]")
          @RequestParam(required = false, defaultValue = "10")
          Integer size) {
    return catalogPresenter.presentPlaylistPage(catalog.getUserPlaylists(page - 1, size));
  }
}
