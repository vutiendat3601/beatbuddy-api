package vn.io.vutiendat3601.beatbuddy.api.common.type;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchRequest {
  private String query;
  private int page;
  private int size;
}
