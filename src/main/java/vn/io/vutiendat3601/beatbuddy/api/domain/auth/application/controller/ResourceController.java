package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.controller;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.ResourceConstant.RESOURCE_CREATED_SUCCESS;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.common.dto.ResponseDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.ResourceDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.presenter.ResourcePresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.AuthResource;

@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@Tag(name = "Auth")
@RequestMapping("v1/auth/resources")
@RestController("authResourceController")
public class ResourceController {
  private final AuthResource authResource;
  private final ResourcePresenter resourcePresenter;

  @Operation(description = "Create Resource", summary = "Create resource")
  @PostMapping
  public ResponseEntity<ResponseDto> createResource(
      @RequestParam String urn, @RequestParam String name) {
    authResource.createResource(urn, name);
    return resourcePresenter.presentResponseOk(RESOURCE_CREATED_SUCCESS);
  }

  @Operation(description = "Get Resource", summary = "Get resource")
  @GetMapping
  public ResponseEntity<ResourceDto> getResource(
      @NotBlank(message = "urn is required") @RequestParam String urn) {
    final Resource resource = authResource.getResource(urn);
    return resourcePresenter.presentResourceDto(resource);
  }
}
