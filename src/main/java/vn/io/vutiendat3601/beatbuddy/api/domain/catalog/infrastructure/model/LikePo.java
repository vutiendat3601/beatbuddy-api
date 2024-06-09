package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.io.vutiendat3601.beatbuddy.api.common.entity.AuditPo;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.converter.StringListConverter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "likes")
public class LikePo extends AuditPo {
  @Id
  @GeneratedValue(generator = "pg-uuid")
  private UUID pkId;

  @Convert(converter = StringListConverter.class)
  private List<String> urns = new LinkedList<>();

  private String ownerId;

  public LikePo(List<String> urns, String ownerId) {
    this.urns = urns;
    this.ownerId = ownerId;
  }
}
