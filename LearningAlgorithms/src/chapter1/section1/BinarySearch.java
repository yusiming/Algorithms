package chapter1.section1;

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
        // 数组开始下标
        int start = 0;
        // 数组结束下标
        int end = a.length - 1;
        // 若key在数组在中，对应的下标在start和end之间
        while (start <= end) {
            int middle = start + (end - start) / 2;
            /*
             * 若key的值小于数组中间值，那么key在前半部分，缩小了范围
             * 若key的值大于数组中间值，那么key在后半部分，缩小了范围
             * */
            if (key < a[middle]) {
                // start位置不变，更新end的值为middle  - 1
                end = middle - 1;
            } else if (key > a[middle]) {
                // end位置不变，更新start的值为middle + 1
                start = middle + 1;
            } else {
                // 返回middle值，
                return middle;
            }
        }
        // 若不存在于数组中，返回-1
        return -1;
    }
}
