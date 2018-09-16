package chapter1.section3;

/**
 * @Auther: yusiming
 * @Date: 2018/8/15 12:05
 * @Description: 二分法
 */
public class BinarySearch {
    /**
     * @Description: 查询参数key是否存在于数组a中，若存在返回对应的数组下标，
     * 若不存在返回-1，数组a必须是有序的
     * @auther: yusiming
     * @date: 12:07 2018/8/15
     * @param: [key, a]
     * @return: int
     */
    public static int rank(int key, int[] a) {
        int start = 0;
        int end = a.length - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
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
