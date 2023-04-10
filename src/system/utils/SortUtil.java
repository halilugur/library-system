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

    private static final int MIN_MERGE = 32;

    private static int minRunLength(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }

    private static <T> void insertionSort(List<T> list, int left, int right, Comparator<T> c) {
        for (int i = left + 1; i <= right; i++) {
            T temp = list.get(i);
            int j = i - 1;
            while (j >= left && c.compare(list.get(j), temp) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, temp);
        }
    }

    private static <T> void merge(List<T> list, int l, int m, int r, Comparator<T> c) {
        int len1 = m - l + 1, len2 = r - m;
        List<T> left = new ArrayList<>(len1);
        List<T> right = new ArrayList<>(len2);
        for (int i = 0; i < len1; i++) {
            left.add(list.get(l + i));
        }
        for (int i = 0; i < len2; i++) {
            right.add(list.get(m + 1 + i));
        }

        int i = 0, j = 0, k = l;
        while (i < len1 && j < len2) {
            if (c.compare(left.get(i), right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < len1) {
            list.set(k++, left.get(i++));
        }

        while (j < len2) {
            list.set(k++, right.get(j++));
        }
    }

    public static <T> void sort(List<T> list, Comparator<T> c) {
        int n = list.size();

        int minRun = minRunLength(MIN_MERGE);

        for (int i = 0; i < n; i += minRun) {
            insertionSort(list, i, Math.min(i + minRun - 1, n - 1), c);
        }

        for (int size = minRun; size < n; size <<= 1) {
            for (int left = 0; left < n; left += size << 1) {
                int mid = left + size - 1;
                int right = Math.min(left + (size << 1) - 1, n - 1);
                merge(list, left, mid, right, c);
            }
        }
    }
}
