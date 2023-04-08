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
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class SearchUtil {

    /**
     * Search data in array by id
     *
     * @param <T> data type should extends BaseModel
     * @param array data list
     * @param id number for searching
     * @return index of data
     */
    public static <T extends BaseModel> int find(List<T> array, Integer id) {
        Integer[] ids = array.stream().map(BaseModel::getId).toArray(Integer[]::new);
        return search(ids, id, 0, ids.length - 1);
    }

    /**
     * This method implements the Binary Search
     *
     * @param <T>
     * @param array The array for searching
     * @param object The data for searching
     * @param left The lower of array
     * @param right The upper of array
     * @return the location of the data in array
     */
    private static <T extends Comparable<T>> int search(T array[], T object, int left, int right) {
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
     *
     * @param array
     * @param comparetor
     */
    public static void listAllDataByCompare(List array, Comparator comparetor) {
        List copyArray = new ArrayList(array);
        Collections.copy(copyArray, array);
        Collections.sort(copyArray, comparetor);
        copyArray.forEach(data -> {
            System.out.println(data);
        });
    }

    /**
     *
     * @param mapper
     * @param object
     * @param index
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
            indexList = mapper.getOrDefault(cascadeString, new Integer[]{index});
        } else { 
            //retrive the existing index list from the mapper and add the new 'index'
            indexList = mapper.get(cascadeString);
            indexList = Arrays.copyOf(indexList, indexList.length + 1);
            indexList[indexList.length - 1] = index;
        }
        mapper.put(cascadeString, indexList);
    }

    public static void indexingOfList(Map<String, Integer[]> mapper, Collection collection, Integer index) {
        collection.forEach(genre -> {
            indexingOfObject(mapper, genre, index);
        });
    }

}
