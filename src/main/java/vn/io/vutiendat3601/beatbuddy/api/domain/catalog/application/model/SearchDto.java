package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;

@Schema(name = "Search", description = "Schema for search response")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchDto {
  @Schema(description = "Result of search")
  private Map<String, Pagination<?>> results = new HashMap<>();

  @Schema(description = "Types of results")
  private Set<String> types;

  @Schema(description = "Page number")
  private Integer page;

  @Schema(description = "Size of page")
  private Integer size;
}
