package chapter2.section1;

/**
 * @Auther: yusiming
 * @Date: 2018/8/23 15:13
 * @Description: 插入排序
 */
public class Insertion {
    /**
     * @Description: 从a[1] 到 a[N-1]之间，的每一个a[i]；
     * 从a[0] 到 a[i-1]之间元素的值，比a[i]小，则依此交换它们的位置
     * @auther: yusiming
     * @date: 15:24 2018/8/23
     * @param: [a]
     * @return: void
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // 若a[j-1] < a[j] 交换他们的位置，j 的大小从 i 到 1
            for (int j = i; j > 0 && less(a[j],a[j-1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (Comparable t : a) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
