package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.controller;

import static vn.io.vutiendat3601.beatbuddy.api.domain.auth.constant.UserConstant.USER_ID_LENGTH;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.model.UserDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.presenter.UserPresenter;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.port.incomming.AuthUser;

@Tag(name = "Auth User")
@SecurityRequirement(name = "web")
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/auth/users")
public class AuthUserController {
  private final UserPresenter userPresenter;
  private final AuthUser userResource;

  @Operation(summary = "Get User by id")
  @GetMapping("{id}")
  public ResponseEntity<UserDto> getUser(
      @Length(min = USER_ID_LENGTH, max = USER_ID_LENGTH, message = "Wrong id format") @PathVariable
          String id) {
    return userPresenter.presentUser(userResource.getUserById(id));
  }

  @Operation(summary = "Get User by ids")
  @GetMapping
  public ResponseEntity<List<UserDto>> getUserByIds(@RequestParam List<String> ids) {
    return userPresenter.presentUsers(userResource.getUserByIds(ids));
  }
}
