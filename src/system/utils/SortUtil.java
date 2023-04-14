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

    private SortUtil() {
    }

    /**
     * Sorts a given list using the provided comparator.
     *
     * @param list       The list to be sorted
     * @param comparator The comparator to be used for sorting
     * @param <T>        The type of elements in the list
     */
    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            T temporaryElement = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(temporaryElement, list.get(j)) < 0) {
                list.set((j + 1), list.get(j));
                j--;
            }
            list.set((j + 1), temporaryElement);
        }
    }
}
