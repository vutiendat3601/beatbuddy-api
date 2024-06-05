package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.CatalogPresenter;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.Catalog;

@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RequestMapping("v1/tracks")
@RestController
public class TrackController {
  // private final Catalog catalog;
  // private final CatalogPresenter catalogPresenter;
}
