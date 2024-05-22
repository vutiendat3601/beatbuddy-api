package vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.io.vutiendat3601.beatbuddy.api.common.entity.AuditPo;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.type.AudioQuality;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "audio_files")
public class AudioFilePo extends AuditPo {
  @Id
  @GeneratedValue(generator = "pg-uuid")
  private UUID pkId;

  private String id;

  private String urn;

  private String uri;

  @Enumerated(EnumType.STRING)
  private AudioQuality quality;

  private String key;
}
