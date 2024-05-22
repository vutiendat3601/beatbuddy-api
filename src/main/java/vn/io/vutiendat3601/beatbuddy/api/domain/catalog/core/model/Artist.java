package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model;

import java.time.LocalDate;
import java.util.UUID;

public record Artist(
    UUID pkId,
    String id,
    String urn,
    String name,
    Boolean isVerified,
    Boolean isPublic,
    LocalDate birthDate,
    String description,
    String nationality,
    String biography,
    String thumbnail,
    String background,
    String tags,
    Long totalLikes) {}
