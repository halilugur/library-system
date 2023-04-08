package system.utils;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class StringUtil {

    /**
     *
     * @param string
     * @param maxLength
     * @return
     */
    public static String makeShort(String string, Integer maxLength) {
        return Optional.of(string)
                .filter(str -> str.length() > maxLength)
                .map(str -> str.substring(0, maxLength) + "...")
                .orElse(string);
    }

    /**
     *
     * @param string
     * @return
     */
    public static String makeShort(String string) {
        return makeShort(string, 15);
    }

    /**
     *
     * @param string
     * @return
     */
    public static String makeShort(Collection string) {
        return makeShort(string.toString(), 15);
    }
}
