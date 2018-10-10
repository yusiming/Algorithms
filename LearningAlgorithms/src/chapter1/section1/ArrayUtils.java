package chapter1.section1;

/**
 * 对数组进行操作的一些工具
 *
 * @Auther yusiming
 * @Date 2018/10/10 14:59
 */
public class ArrayUtils<T> {
    /**
     * 颠倒数组元素的顺序
     * 这个方法不能声明为static类型
     * 因为，泛型不能使用在静态方法中
     *
     * @param a 被颠倒顺序的数组
     */
    public void reverse(T[] a) {
        int N = a.length;
        for (int i = 0; i < N / 2; i++) {
            T temp = a[i];
            a[i] = a[N - 1 - i];
            a[N - 1 - i] = temp;
        }
    }
}
