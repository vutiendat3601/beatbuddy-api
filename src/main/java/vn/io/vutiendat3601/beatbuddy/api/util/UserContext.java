package vn.io.vutiendat3601.beatbuddy.api.util;

public class UserContext {
  private static ThreadLocal<String> userPkIdRef = new ThreadLocal<>();
  private static ThreadLocal<String> userIdRef = new ThreadLocal<>();
  private static ThreadLocal<String> jwtAuthenticationTokenRef = new ThreadLocal<>();
  private static ThreadLocal<String> jwtAuthorizationTokenRef = new ThreadLocal<>();

  public static String getUserPkId() {
    return userPkIdRef.get();
  }

  public static void setUserPkId(String userPkId) {
    userPkIdRef.set(userPkId);
  }

  public static String getUserId() {
    return userIdRef.get();
  }

  public static void setUserId(String userId) {
    userIdRef.set(userId);
  }

  public static String getJwtAuthenticationToken() {
    return jwtAuthenticationTokenRef.get();
  }

  public static void setJwtAuthenticationToken(String jwtToken) {
    jwtAuthenticationTokenRef.set(jwtToken);
  }

  public static String getJwtAuthorizationToken() {
    return jwtAuthorizationTokenRef.get();
  }

  public static void setJwtAuthorizationToken(String jwtToken) {
    jwtAuthorizationTokenRef.set(jwtToken);
  }
}
