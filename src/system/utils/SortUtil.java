/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Tolga Baris Pinar
 */
public class SortUtil {
    
    public static <T> void sort(List<T> list, Comparator<T> c) {
        System.out.println("-----------");
        System.out.println("--Insertion Sort starting--");

        //code goes here
        int pos;
        T temp;
        
        for (int i = 1; i < list.size(); i++) {
            temp = list.get(i);
            pos = i;
            T previousValue = list.get(pos-1);
            while (pos > 0 && c.compare(previousValue, temp) > 0) {
                list.set(pos, list.get(pos-1));
                --pos;
            }
            list.set(pos, temp);
            
        }
    }

    
}


//private static final int MIN_MERGE = 32;
//
//    private static int minRunLength(int n) {
//        int r = 0;
//        while (n >= MIN_MERGE) {
//            r |= n & 1;
//            n >>= 1;
//        }
//        return n + r;
//    }
//
//    private static <T> void insertionSort(List<T> list, int left, int right, Comparator<T> c) {
//        for (int i = left + 1; i <= right; i++) {
//            T temp = list.get(i);
//            int j = i - 1;
//            while (j >= 0 && c.compare(list.get(j), temp) > 0) {
//                list.set(j + 1, list.get(j));
//                j--;
//            }
//            list.set(j + 1, temp);
//        }
//    }
//
//    private static <T> void merge(List<T> list, int left, int mid, int right, Comparator<T> c) {
//        int leftSize = mid - left + 1, rightSize = Math.max(right - mid,0) ; //43. satırda diziye eksi boyutunda uzunluk vermeye çalışıyor bundan patlıyor. gerisi sende
//        List<T> leftList = new ArrayList<>(leftSize);
//        List<T> rightList = new ArrayList<>(rightSize);
//        for (int i = 0; i < leftSize; i++) {
//            leftList.add(list.get(left + i));
//        }
//        for (int i = 0; i < rightSize; i++) {
//            rightList.add(list.get(mid + 1 + i));
//        }
//
//        int i = 0, j = 0, k = left;
//        while (i < leftSize && j < rightSize) {
//            if (c.compare(leftList.get(i), rightList.get(j)) <= 0) { //>= 0 maybe like this
//                list.set(k++, leftList.get(i++));
//            } else {
//                list.set(k++, rightList.get(j++));
//            }
//        }
//
//        while (i < leftSize) {
//            list.set(k++, leftList.get(i++));
//        }
//
//        while (j < rightSize) {
//            list.set(k++, rightList.get(j++));
//        }
//    }
//
//    public static <T> void sort(List<T> list, Comparator<T> c) {
//        int n = list.size();
//
//        int minRun = minRunLength(MIN_MERGE);
//
//        for (int i = 0; i < n; i += minRun) {
//            insertionSort(list, i, Math.min(i + minRun - 1, n - 1), c);
//        }
//
//        for (int size = minRun; size < n; size <<= 1) {
//            for (int left = 0; left < n; left += size << 1) {
//                int mid = left + size - 1;
//                int right = Math.min(left + (size << 1) - 1, n - 1);
//                merge(list, left, mid, right, c);
//            }
//        }
//    }