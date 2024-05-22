package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.ArtistPo;

public interface ArtistJpaRepository extends JpaRepository<ArtistPo, UUID> {
  Optional<ArtistPo> findById(String id);

  List<ArtistPo> findByIdIn(Iterable<String> ids);

  Page<ArtistPo> findByOrderByTotalLikesDesc(Pageable pageable);

  // Page<ArtistPo> findByTagsContainingIgnoreCase(String query, Pageable pageable);

  @Query(
      value =
          """
          SELECT * FROM artists WHERE tags ILIKE %:query% OR tsv @@ to_tsquery(:tsvQuery)
          ORDER BY total_likes DESC
          """,
      nativeQuery = true)
  Page<ArtistPo> findByTagOrTsvOrderByTotalLikesDesc(
      @Param("query") String query, @Param("tsvQuery") String tsvQuery, Pageable pageable);
}
