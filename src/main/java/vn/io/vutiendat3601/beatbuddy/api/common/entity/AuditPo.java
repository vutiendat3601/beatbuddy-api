package vn.io.vutiendat3601.beatbuddy.api.common.entity;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@MappedSuperclass
public abstract class AuditPo {
  @CreatedDate protected ZonedDateTime createdAt;

  @LastModifiedDate protected ZonedDateTime updatedAt;

  @CreatedBy protected String createdBy;

  @LastModifiedBy protected String updatedBy;
}
