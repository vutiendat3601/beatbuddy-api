package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.io.vutiendat3601.beatbuddy.api.common.entity.AuditPo;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.converter.StringListConverter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "playlists")
public class PlaylistPo extends AuditPo {
  @Id
  @GeneratedValue(generator = "pg-uuid")
  private UUID pkId;

  private String id;

  private String urn;

  private String name;

  private String thumbnail;

  private String description;

  private Boolean isPublic = true;

  private String ownerId;

  private Long totalLikes = 0L;

  private Boolean isDeleted = false;

  @Convert(converter = StringListConverter.class)
  private List<String> itemUrns = new LinkedList<>();

  public PlaylistPo(
      String id,
      String urn,
      String name,
      String thumbnail,
      String description,
      Boolean isPublic,
      String ownerId,
      Long totalLikes,
      Boolean isDeleted,
      List<String> itemUrns) {
    this.id = id;
    this.urn = urn;
    this.name = name;
    this.thumbnail = thumbnail;
    this.description = description;
    this.isPublic = isPublic;
    this.ownerId = ownerId;
    this.totalLikes = totalLikes;
    this.isDeleted = isDeleted;
    this.itemUrns = itemUrns;
  }
}
