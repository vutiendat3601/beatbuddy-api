package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model;

import java.net.URI;
import java.util.Set;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource.ScopePermission;

public record Resource(
    String urn,
    String name,
    String type,
    User owner,
    Set<URI> uris,
    Set<String> scopes,
    Set<ScopePermission> permissions) {}
