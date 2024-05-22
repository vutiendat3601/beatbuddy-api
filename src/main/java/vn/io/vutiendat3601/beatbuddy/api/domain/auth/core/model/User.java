package vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model;

import java.util.Set;

import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource.IdentityProvider;

public record User(
    String pkId,
    String id,
    String urn,
    String firstName,
    String lastName,
    String username,
    String email,
    Boolean isEmailVerified,
    String picture,
    Set<IdentityProvider> linkedIdentityProviders) {}
