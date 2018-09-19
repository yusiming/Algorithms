package chapter2.section1;

/**
 * @Auther: yusiming
 * @Date: 2018/9/19 21:27
 * @Description:
 */
public class Quick {
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

    public static int partition(Comparable[] a, int start, int end) {
        int i = start;
        int j = end + 1;
        Comparable v = a[i];
        while (true) {
            while (less(a[++i], v)) {
                if (i == end) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == start) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, start, j);
        return j;
    }

    // 排序方法
    public static void sort(Comparable[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int j = partition(a, start, end);
        sort(a, start, j - 1);
        sort(a, j + 1, end);
    }

    public static void main(String[] args) {
        Comparable[] a = {8, 4, 7, 3, 10, 6, 5, 9};
        sort(a,0,7);
        for (int i = 0; i < 8; i++) {
            System.out.println(a[i]);
        }
    }
}
