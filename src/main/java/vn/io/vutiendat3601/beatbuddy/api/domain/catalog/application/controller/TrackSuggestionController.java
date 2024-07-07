package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.CatalogService;

@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RequestMapping("v1/track-suggestions")
@RestController
public class TrackSuggestionController {
  private final CatalogService catalog;
  private final CatalogPresenter catalogPresenter;
}
