package system.utils;

import java.util.Comparator;
import java.util.List;

/**
 * A utility class for sorting a list of elements using a custom comparator.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class SortUtil {

    /**
     * Sorts a given list using the provided comparator.
     *
     * @param list The list to be sorted
     * @param comparator The comparator to be used for sorting
     * @param <T> The type of elements in the list
     */
    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        int position;
        T temporaryElement;
        for (int i = 1; i < list.size(); i++) {
            temporaryElement = list.get(i);
            position = i;
            T previousValue = list.get(position - 1);
            while (position > 0 && comparator.compare(previousValue, temporaryElement) > 0) {
                list.set(position, list.get(position - 1));
                --position;
            }
            list.set(position, temporaryElement);
        }
    }
}
