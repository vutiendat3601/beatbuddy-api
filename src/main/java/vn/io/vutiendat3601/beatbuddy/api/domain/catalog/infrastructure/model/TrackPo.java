package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.io.vutiendat3601.beatbuddy.api.common.entity.AuditPo;
import vn.io.vutiendat3601.beatbuddy.api.converter.SetStringConverter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tracks")
public class TrackPo extends AuditPo {
  @Id
  @GeneratedValue(generator = "pg-uuid")
  private UUID pkId;

  private String id;

  private String urn;

  private String name;

  private Integer durationSec;

  private String description;

  private String releasedDate;

  private String thumbnail;

  private Boolean isPublic;

  private Boolean isPlayable;

  @Column(name = "audio_file_ids")
  @Convert(converter = SetStringConverter.class)
  private Set<String> audioFileIds = new LinkedHashSet<>();

  private Long totalLikes;

  private String tags;

  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "track_artist",
      joinColumns = @JoinColumn(name = "track_pk_id"),
      inverseJoinColumns = @JoinColumn(name = "artist_pk_id"))
  private List<ArtistPo> artists = new LinkedList<>();

  public TrackPo(UUID pkId) {
    this.pkId = pkId;
  }
}
