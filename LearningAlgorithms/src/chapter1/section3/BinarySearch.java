package chapter1.section3;

/**
 * 适用迭代完成二分法
 *
 * @Auther yusiming
 * @Date 2018/8/15 12:05
 */
public class BinarySearch {
    /**
     * 判断某一整数是否存在于数组中
     *
     * @param key 键
     * @param a   数组
     * @return 若key存在于数组中，返回对应的数组下标，若不存在，返回-1
     */
    public static int rank(int key, int[] a) {
        int start = 0;
        int end = a.length - 1;
        int middle;
        while (start <= end) {
            middle = start + (end - start) / 2;
            if (a[middle] > key) {
                end = middle - 1;
            } else if (a[middle] < key) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
