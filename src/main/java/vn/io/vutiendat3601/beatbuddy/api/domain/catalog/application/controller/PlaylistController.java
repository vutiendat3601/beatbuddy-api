package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_ADD_ITEM_SUCCESS;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_CREATE_SUCCESS;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_ID_LENGTH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.common.dto.ResponseDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDetailDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.PlaylistPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.PlaylistService;

@Tag(name = "Playlist")
@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RequestMapping("v1/playlists")
@RestController
public class PlaylistController {
  private final PlaylistService playlistService;
  private final CatalogPresenter commonPresenter;
  private final PlaylistPresenter playlistPresenter;

  @Operation(
      summary = "Create Playlist",
      description = "Create a new Playlist",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)))
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ResponseDto> createPlaylist(
      @NotBlank(message = "name is required") String name,
      @NotNull(message = "isPublic is true or false") Boolean isPublic,
      String thumbnail,
      String description) {
    playlistService.createPlaylist(name, isPublic, thumbnail, description);
    return commonPresenter.presentResponseDtoOk(PLAYLIST_CREATE_SUCCESS);
  }

  @Operation(summary = "Add Items to Playlist", description = "Add Items to Playlist")
  @PutMapping(path = "{id}/add-item", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseDto> addTrackToPlaylist(
      @Length(min = PLAYLIST_ID_LENGTH, max = PLAYLIST_ID_LENGTH, message = "Wrong id format")
          @PathVariable
          String id,
      @RequestBody @NotEmpty List<String> itemUrns) {
    playlistService.addItemToPlaylist(id, itemUrns);
    return commonPresenter.presentResponseDtoOk(PLAYLIST_ADD_ITEM_SUCCESS);
  }

  @Operation(summary = "Get Playlist", description = "Get a Playlist in detail by id")
  @GetMapping(path = "{id}")
  public ResponseEntity<PlaylistDetailDto> getPlaylistDetail(
      @Length(min = PLAYLIST_ID_LENGTH, max = PLAYLIST_ID_LENGTH, message = "Wrong id format")
          @PathVariable
          String id) {
    return playlistPresenter.presentPlaylistDetailDto(playlistService.getPlaylistById(id));
  }

  // @Operation(
  //     summary = "Create Playlist Permission Request",
  //     description = "Create Playlist Permission request")
  // @PostMapping(path = "{id}/permissions/request")
  // public ResponseEntity<ResponseDto> createPermissionRequest(@PathVariable String id) {
  //   return commonPresenter.presentResponseDtoOk(PLAYLIST_CREATE_PERMISSION_REQUEST_SUCCESS);
  // }
}
