package system.utils;

import java.util.Collection;
import java.util.Optional;

/**
 * A utility class for manipulating strings.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class StringUtil {

    /**
     * Shortens a string to a specified maximum length by adding ellipses at the
     * end.
     *
     * @param string The string to shorten.
     * @param maxLength The maximum length of the shortened string.
     * @return The shortened string with ellipses added at the end, or the
     * original string if it is already shorter than the maximum length.
     */
    public static String makeShort(String string, Integer maxLength) {
        return Optional.of(string)
                .filter(str -> str.length() > maxLength)
                .map(str -> str.substring(0, maxLength) + "...")
                .orElse(string);
    }

    /**
     * Shortens a given string to a maximum length of 15 characters.
     *
     * @param string The string to shorten.
     * @return The shortened string.
     */
    public static String makeShort(String string) {
        return makeShort(string, 15);
    }

    /**
     * Shortens a given string array to a maximum length of 15 characters.
     *
     * @param collection The string collection to shorten.
     * @return The shortened string.
     */
    public static String makeShort(Collection collection) {
        return makeShort(collection.toString(), 15);
    }
}
