package chapter2.section1;

/**
 * @Auther: yusiming
 * @Date: 2018/9/17 21:30
 * @Description:
 */
public class Merge {
    // 使用一个数组来存放临时数据
    private static Comparable[] temp;

    // 任何实现了Comparable 接口的数据类型，都可以使用compareTo 方法进行比较
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // 交换数据
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // 将数组a 按升序排序
    public static void sort(Comparable[] a) {
        int N = a.length;
        temp = new Comparable[N];
        // 将数组a 从 0 dao N -1 位置排序
        sort(a, 0, N - 1);
    }

    // 递归方法，来实现分治思想,对数组a 的 start 到  end 位置排序
    public static void sort(Comparable[] a, int start, int end) {
        int middle = start + (end - start) / 2;
        // 递归的结束条件，数组大小为一
        if (start >= end) {
            return;
        }
        sort(a, start, middle);
        sort(a, middle + 1, end);
        merge(a, start, middle, end);
    }

    // 这个方法将两个小数组归并为一个大数组,
    // start 到 middle 为第一个数组， middle 到 end 为第二个数组
    public static void merge(Comparable[] a, int start, int middle, int end) {
        // 第一个数组的索引
        int i = start;
        // 第二个数组的索引
        int j = middle + 1;
        for (int k = start; k <= end; k++) {
            // 将数据复制到辅助数组
            temp[k] = a[k];
        }
        // 循环执行，end - start 次，每次将一个数据放到相对有序的位置上
        for (int k = start; k <= end; k++) {
            if (i > middle) {
                // 若 i > middle 证明第一个小数组数组已经没有了，直接放第二个数组的
                // 索引加一
                a[k] = temp[j];
                j++;
            } else if (j > end) {
                // 若 j > end 证明第二个小数组数组已经没有了，直接放第一个数组的
                a[k] = temp[i];
                i++;
            } else if (less(temp[i],temp[j])) {
                // 若 a[i] < a[j] ,证明a[i] 要被插入 数组 a 中,数组的索引加一
                a[k] = temp[i];
                i++;
            } else {
                // 若 a[j] < a[i] ,证明a[j] 要被插入 数组 a 中,数组的索引加一
                a[k] = temp[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] a = {8, 4, 7, 3, 10, 6, 5, 9};
        sort(a);
        for (int i = 0; i < 8; i++) {
            System.out.println(a[i]);
        }
    }
}
