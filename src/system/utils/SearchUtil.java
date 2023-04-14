package system.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import system.models.BaseModel;

/**
 * A utility class for searching and indexing objects in a list.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class SearchUtil {

    private SearchUtil() {
    }

    /**
     * Searches for an element in a list of BaseModel objects by their ID.
     *
     * @param array The list of BaseModel objects to search through
     * @param id    The ID of the element to search for
     * @param <T>   A type that extends BaseModel
     * @return The index of the element in the list, or -1 if it is not found
     */
    public static <T extends BaseModel> int find(List<T> array, Integer id) {
        Integer[] ids = array.stream().map(BaseModel::getId).toArray(Integer[]::new);
        return search(ids, id, 0, ids.length - 1);
    }

    /**
     * Searches for an object in a sorted array using binary search.
     *
     * @param array  The sorted array to search in.
     * @param object The object to search for.
     * @param left   The leftmost index of the array to search in.
     * @param right  The rightmost index of the array to search in.
     * @param <T>    The type of the objects in the array, which must implement Comparable.
     * @return The index of the object in the array, or -1 if it is not found.
     */
    private static <T extends Comparable<T>> int search(T[] array, T object, int left, int right) {
        if (right < left) {
            return -1;
        }
        int mid = (left + right) >>> 1;
        int compare = object.compareTo(array[mid]);

        if (compare == 0) {
            return mid;
        } else if (compare < 0) {
            return search(array, object, left, mid - 1);
        } else {
            return search(array, object, mid + 1, right);
        }
    }

    /**
     * Sorts and prints a copy of the given list using the provided comparator.
     *
     * @param array      The list to be sorted and printed
     * @param comparator The comparator to be used for sorting
     */
    public static void listAllDataByCompare(List array, Comparator comparator) {
        List copyArray = new ArrayList<>(array);
        Collections.copy(copyArray, array);
        SortUtil.sort(copyArray, comparator);
        copyArray.forEach(System.out::println);
    }

    /**
     * Indexes an object in a given mapper with a given index.
     *
     * @param mapper The mapper to index the object in.
     * @param object The object to index.
     * @param index  The index to use for the object.
     */
    public static void indexingOfObject(Map<String, Integer[]> mapper, Object object, Integer index) {
        //to hold the index of the object
        Integer[] indexList;
        String cascadeString = Optional.of(object)
                .filter(String.class::isInstance)
                .map(Object::toString)
                .map(String::toLowerCase)
                .orElse(object.toString());
        //to create a new integer array with the current index if the mapper not contain a key
        if (mapper.get(cascadeString) == null) {
            indexList = mapper.getOrDefault(cascadeString, new Integer[] {index});
        } else {
            //retrive the existing index list from the mapper and add the new 'index'
            indexList = mapper.get(cascadeString);
            indexList = Arrays.copyOf(indexList, indexList.length + 1);
            indexList[indexList.length - 1] = index;
        }
        mapper.put(cascadeString, indexList);
    }

    /**
     * Indexes each element in a collection with a given index and stores it in a map.
     *
     * @param mapper     The map to store the indexed elements in
     * @param collection The collection to index
     * @param index      The index to assign to each element in the collection
     */
    public static void indexingOfList(Map<String, Integer[]> mapper, Collection<?> collection, Integer index) {
        collection.forEach(genre -> indexingOfObject(mapper, genre, index));
    }
}
