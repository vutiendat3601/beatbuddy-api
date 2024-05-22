// package vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.adapter;

// import java.net.URI;
// import java.util.LinkedList;
// import java.util.List;

// // import org.springframework.stereotype.Repository;

// import lombok.RequiredArgsConstructor;
// import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.AudioFile;
// import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.port.outgoing.AudioFileRepository;
// import
// vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.repository.AudioFileDigitalOceanRepository;

// @RequiredArgsConstructor
// // @Repository
// public class AudioFileDigitalOceanRepositoryAdapter implements AudioFileRepository {
//   private final AudioFileDigitalOceanRepository audioFileDigitalOceanRepo;
//   private final int MAX_DAY_EXPIRATION = 7;

//   @Override
//   public List<AudioFile> findByKeyOrTrackId(String key,String trackId) {
//     final List<AudioFile> results = new LinkedList<>();
//      audioFileDigitalOceanRepo
//         .findByKey(key)
//         .ifPresent(s3Object-> {
//           final List<URI> uris =
//                   audioFileDigitalOceanRepo.generatePresignedUris(
//                       s3Object.getKey(), MAX_DAY_EXPIRATION);
//               results.add(new AudioFile(key,uris));
//         });
//     return results;
//   }
// }
