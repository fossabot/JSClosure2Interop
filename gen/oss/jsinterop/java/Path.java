package oss.jsinterop.java;

import java.lang.String;
import jsinterop.annotations.JsType;
import jsinterop.annotations.JsPackage;

@JsType(isNative = true, name = "path", namespace = JsPackage.GLOBAL)
public class Path {
  public static String delimiter;
  public static String sep;

  public static native String basename(String p0, String p1);

  public static native String basename(String p0);

  public static native String dirname(String p0);

  public static native String extname(String p0);

  public static native boolean isAbsolute(String p0);

  public static native String join(String... p0);

  public static native String normalize(String p0);

  public static native String relative(String p0, String p1);

  public static native String resolve(String p0, String p1);

  public static native String resolve(String p0);
}
