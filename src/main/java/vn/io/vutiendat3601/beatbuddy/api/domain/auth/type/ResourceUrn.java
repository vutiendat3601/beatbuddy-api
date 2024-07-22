package vn.io.vutiendat3601.beatbuddy.api.domain.auth.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResourceUrn {
  private int numberOfParts;

  private int typePartIndex;

  private int idPartIndex;

  private String partSeparator;
}
