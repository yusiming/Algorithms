package chapter1.section1;

import java.util.Arrays;

/**
 * 二分法
 *
 * @Auther yusiming
 * @Date 2018/10/10 15:15
 */
public class BinarySearch {
    public void rank(int key, int[] a) {
        Arrays.sort(a);
        rank(key, a, 0, a.length - 1);
    }

    private int rank(int key, int[] a, int start, int end) {
        if (start > end) {
            return -1;
        }
        int middle = start + (end - start) / 2;
        if (a[middle] < key) {
            return rank(key, a, middle + 1, end);
        } else if (a[middle] > key) {
            return rank(key, a, start, middle - 1);
        } else {
            return middle;
        }
    }
}
