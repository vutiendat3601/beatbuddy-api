package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_ID_LENGTH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.TrackDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.Catalog;

// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.CatalogPresenter;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.Catalog;

@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RequestMapping("v1/tracks")
@RestController
public class TrackController {
  private final Catalog catalog;
  private final CatalogPresenter catalogPresenter;

  @Tag(name = "Track")
  @Operation(summary = "Get Track", description = "Get a Track by id")
  @GetMapping("{id}")
  public ResponseEntity<TrackDto> getTrack(
      @Length(min = TRACK_ID_LENGTH, max = TRACK_ID_LENGTH, message = "Wrong id format")
          @PathVariable
          String id) {
    return catalogPresenter.presentTrack(catalog.getTrackById(id));
  }

  @Tag(name = "Track")
  @Operation(summary = "Get Tracks", description = "Get several Tracks by ids")
  @GetMapping
  public ResponseEntity<List<TrackDto>> getTracks(
      @Size(min = 1, max = 100) @RequestParam
          List<
                  @Length(min = TRACK_ID_LENGTH, max = TRACK_ID_LENGTH, message = "Wrong id format")
                  String>
              ids) {
    return catalogPresenter.presentTracks(catalog.getTrackByIds(ids));
  }
}
