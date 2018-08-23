package chapter2.section1;

/**
 * @Auther: yusiming
 * @Date: 2018/8/23 14:39
 * @Description: 选择排序，从第i位置开始，到N ，找到 i 到 N 之间的最小的元素，将其排在i的位置，
 */
public class Selection {
    // 选择排序
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // 设置最小元素的索引为i
            int min = i;
            // 若i+1 到 N 中有元素的主键比i的主键小，则将索引赋值给min，
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            // 此时得到的i时，i 到 N 之间的最小的元素的索引，将a[i] 与 a[min] 交换位置，
            exch(a, i, min);
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
