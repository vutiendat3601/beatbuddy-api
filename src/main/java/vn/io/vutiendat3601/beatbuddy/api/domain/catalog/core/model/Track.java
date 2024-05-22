package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record Track(
    UUID pkId,
    String id,
    String urn,
    String name,
    Integer durationSec,
    String description,
    String releasedDate,
    String thumbnail,
    Boolean isPublic,
    Boolean isPlayable,
    Long totalLikes,
    Set<String> audioFileIds,
    String tags,
    List<Artist> artists) {}
