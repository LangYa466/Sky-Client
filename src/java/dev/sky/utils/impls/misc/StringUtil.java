package dev.sky.utils.impls.misc;

/**
 * @author LangYa466
 * @date 2024/4/18 21:04
 */

public class StringUtil {
    private static boolean isEmojiCharacter(char codePoint) {
        return codePoint == 0 || codePoint == '\t' || codePoint == '\n' || codePoint == '\r' || codePoint >= ' ' && codePoint <= '\ud7ff' || codePoint >= '\ue000' && codePoint <= '\ufffd' || codePoint >= 65536 && codePoint <= 1114111;
    }

    public static boolean isBlank(String s) {
        if (s != null) {
            for (int i = 0; i < s.length(); ++i) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }

        }
        return true;
    }

    public static String filterEmoji(String source) {
        if (isBlank(source)) {
            return source;
        } else {
            StringBuilder buf = null;
            int len = source.length();

            for (int i = 0; i < len; ++i) {
                char codePoint = source.charAt(i);
                if (isEmojiCharacter(codePoint)) {
                    if (buf == null) {
                        buf = new StringBuilder(source.length());
                    }

                    buf.append(codePoint);
                }
            }

            if (buf == null) {
                return source;
            } else if (buf.length() == len) {
                return source;
            } else {
                return buf.toString();
            }
        }
    }

}
