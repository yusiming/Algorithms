package chapter1.section1;

import java.util.Arrays;

/**
 * 能够在整数集合中查找指定的数的一种抽象数据类型
 *
 * @Auther yusiming
 * @Date 2018/10/9 16:14
 */
public class StaticSETofInts {
    private int a[];

    /**
     * 通过参数初始化一个整型数值的集合，
     *
     * @param keys 整型数组
     */
    public StaticSETofInts(int[] keys) {
        int length = keys.length;
        a = new int[length];
        // 复制数组元素
        System.arraycopy(keys, 0, a, 0, length);
        // 排序
        Arrays.sort(a);
    }

    /**
     * 判断key是否存在于集合中
     *
     * @param key 要判断的整型数值
     * @return 当key在集合中时返回true，否则返回flase
     */
    public boolean contains(int key) {
        return rank(key) != -1;
    }

    /**
     * 使用二分法判断key是否存在于数组a中
     *
     * @param key 整型数值
     * @return 若存在，返回对于的下标，若不存在返回-1
     */
    private int rank(int key) {
        int start = 0;
        int end = a.length - 1;
        int middle;
        while (start <= end) {
            middle = start + (end - start) / 2;
            if (key < a[middle]) {
                end = middle - 1;
            } else if (key > a[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = new int[100];
        // 随机生成100个数，
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }
        StaticSETofInts staticSETofInts = new StaticSETofInts(a);
        // 查看1-100中，有那些数存在于数组a中
        for (int i = 0; i < 100; i++) {
            if (staticSETofInts.contains(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
