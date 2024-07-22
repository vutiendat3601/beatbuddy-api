package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.ScopePermission;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistPermissionRequestDto {
  @NotEmpty @NotNull private List<ScopePermission> permissions;
}
