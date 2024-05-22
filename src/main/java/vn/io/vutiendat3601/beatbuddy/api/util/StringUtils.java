package vn.io.vutiendat3601.beatbuddy.api.util;

public class StringUtils {
  private static final String ALPHABET =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public static String makeRandomString(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int index = (int) (ALPHABET.length() * Math.random());
      sb.append(ALPHABET.charAt(index));
    }
    return sb.toString();
  }
}
