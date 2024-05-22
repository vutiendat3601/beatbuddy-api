package vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.repository;

import static com.amazonaws.HttpMethod.GET;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// import org.springframework.stereotype.Repository;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3Object;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.application.config.AmazonS3Config;

// @Repository
@RequiredArgsConstructor
public class AudioFileDigitalOceanRepository {
  private final AmazonS3Config amazonS3Config;

  private final AmazonS3 amazonS3;

  public Optional<S3Object> findByKey(String key) {
    return Optional.ofNullable(amazonS3.getObject(amazonS3Config.getBucketName(), key));
  }

  public List<URI> generatePresignedUris(String key, int numOfDays) {
    GeneratePresignedUrlRequest presignedUrlRequest =
        new GeneratePresignedUrlRequest(amazonS3Config.getBucketName(), key, GET)
            .withExpiration(Date.from(ZonedDateTime.now().plusDays(numOfDays).toInstant()));
    try {
      final URL presignedUrl = amazonS3.generatePresignedUrl(presignedUrlRequest);
      final URI presignedUri = presignedUrl.toURI();
      final String query = presignedUri.getRawQuery();
      final List<URI> cdnUris =
          amazonS3Config.getCdnBaseUrls().stream()
              .map(baseUrl -> URI.create(baseUrl + "/" + key + "?" + query))
              .toList();
      final Set<URI> uris = new HashSet<>(cdnUris);
      uris.add(presignedUri);
      return new LinkedList<>(uris);
    } catch (SdkClientException | URISyntaxException e) {
      e.printStackTrace();
    }
    return new LinkedList<>();
  }
}
