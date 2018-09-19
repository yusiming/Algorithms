package chapter2.section1;

/**
 * @Auther: yusiming
 * @Date: 2018/9/19 22:31
 * @Description: 三向切分的快速排序
 */
public class Quick3Ways {
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

    public static void sort(Comparable[] a, int start, int end) {
        while (start >= end) return;
        int i = start;
        int j = start + 1;
        int k = end;
        Comparable v = a[start];
        while (j <= k) {
            int comp = a[j].compareTo(v);
            // 若小于零，将数据放到i 指针之前
            if (comp < 0) {
                exch(a, j, i);
                j++;
                i++;
            } else if (comp > 0) {
                exch(a, j, k);
                k--;
            } else {
                j++;
            }
        }
        sort(a, start, j - 1);
        sort(a, k + 1, end);
    }

    public static void main(String[] args) {
        Comparable[] a = {8, 4, 7, 3, 10, 6, 5, 9};
        sort(a, 0, 7);
        for (int i = 0; i < 8; i++) {
            System.out.println(a[i]);
        }
    }
}
