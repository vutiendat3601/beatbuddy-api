package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.controller;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.UserConstant.USER_ID_LENGTH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.CatalogPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.Catalog;

@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RequestMapping("v1/users")
@RestController
public class UserController {
  private final Catalog catalog;
  private final CatalogPresenter catalogPresenter;

  @Tag(name = "User")
  @Operation(summary = "Get User", description = "Get a User by id")
  @GetMapping(path = "{id}")
  public ResponseEntity<UserDto> getUser(
      @Length(min = USER_ID_LENGTH, max = USER_ID_LENGTH, message = "Wrong id format") @PathVariable
          String id) {
    return catalogPresenter.presentUser(catalog.getUserById(id));
  }
}
