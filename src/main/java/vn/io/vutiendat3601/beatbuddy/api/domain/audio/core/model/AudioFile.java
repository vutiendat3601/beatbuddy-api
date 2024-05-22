package vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model;

import java.net.URI;
import java.util.Set;

public record AudioFile(String key, Set<URI> uris) {}
