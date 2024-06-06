package vn.io.vutiendat3601.beatbuddy.api.application.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.io.vutiendat3601.beatbuddy.api.util.UserContext;

public class DecodedJwtContextFilter extends OncePerRequestFilter {
  private static final String USER_ID_CLAIM = "user_id";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication instanceof JwtAuthenticationToken) {
      final JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken) authentication;
      final Jwt jwt = jwtAuthentication.getToken();
      final String pkId = jwt.getSubject();
      final String userId = jwt.getClaimAsString(USER_ID_CLAIM);

      UserContext.setUserPkId(pkId);
      UserContext.setUserId(userId);
      UserContext.setJwtAuthorizationToken(jwt.getTokenValue());
    }
    filterChain.doFilter(request, response);
  }
}
