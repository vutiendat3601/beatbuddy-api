package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.LikeDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.PlaylistDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.PlaylistPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.CatalogService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.PlaylistService;

@Tag(name = "Me")
@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RequestMapping("v1/me")
@RestController
public class MeController {
  private final CatalogService catalog;
  private final PlaylistService playlistService;
  private final PlaylistPresenter playlistPresenter;
  private final CatalogPresenter commonPresenter;

  @Operation(
      summary = "Get Authenticated User Playlists",
      description = "Get Authenticated User Playlists")
  @GetMapping(path = "playlists")
  public ResponseEntity<Pagination<PlaylistDto>> getUserPlaylists(
      @Min(value = 1, message = "page must be greater than or equal to 1")
          @RequestParam(required = false, defaultValue = "1")
          Integer page,
      @Range(min = 1, max = 50, message = "size must be in range [1, 50]")
          @RequestParam(required = false, defaultValue = "10")
          Integer size) {
    return playlistPresenter.presentPlaylistDtoPage(
        playlistService.getUserPlaylists(page - 1, size));
  }

  @Tag(name = "Me")
  @Operation(summary = "Get User Like Detail", description = "Get current User's Like in detail")
  @GetMapping("like")
  public ResponseEntity<LikeDto> getCurrentUserLikeDetail() {
    return commonPresenter.presentLikeDto(catalog.getCurrentUserLikeDetail());
  }
}
