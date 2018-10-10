package chapter1.section3;

/**
 * 使用数组实现定容String栈
 *
 * @Auther yusiming
 * @Date 2018/8/16 10:40
 */
public class FixedCapacityStackOfStrings {
    /**
     * 使用数组存储数据
     */
    private String[] a;
    /**
     * 使用N表示栈中字符串的个数，同时也充当了栈顶的索引
     */
    private int N;

    /**
     * 创建一个固定大小的栈
     *
     * @param cap 栈的大小
     */
    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
        this.N = 0;
    }

    /**
     * 向栈中添加一个字符串
     *
     * @param s 被添加的字符串
     */
    public void push(String s) {
        a[N++] = s;
    }

    /**
     * @return 返回最近添加的一个元素
     */
    public String pop() {
        return a[--N];
    }

    /**
     * @return 若栈为空，返回true，否则返回false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回栈中的元素的个数
     *
     * @return 元素的个数
     */
    public int size() {
        return N;
    }
}
