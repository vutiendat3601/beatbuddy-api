package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.io.vutiendat3601.beatbuddy.api.common.entity.AuditPo;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "artists")
public class ArtistPo extends AuditPo {
  @Id private UUID pkId;

  private String id;

  private String urn;

  private String name;

  private Boolean isVerified = false;

  private Boolean isPublic = true;

  private LocalDate birthDate;

  private String description;

  private String nationality;

  private String biography;

  private String thumbnail;

  private String background;

  private String tags;

  private Long totalLikes = 0L;
}
