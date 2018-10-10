package chapter1.section3;

import edu.princeton.cs.algs4.In;

/**
 * 使用数组实现存储任意引用类型的栈
 * <p>
 * 测试所使用的数据
 * to be or not to - be - - that - - - is
 *
 * @Auther yusiming
 * @Date 2018/8/16 10:40
 */
public class FixedCapcityStack<T> {
    /**
     * 存储数据
     */
    private T[] a;
    /**
     * 表示数组的大小以及栈顶索引
     */
    private int N = 0;
    /**
     * 默认栈的大小
     */
    private static final int DEFAULT_SIZE = 20;

    /**
     * 初始化一个默认大小的栈
     */
    public FixedCapcityStack() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public FixedCapcityStack(int cap) {
        a = (T[]) new Object[cap];
    }

    /**
     * 判断数组是否为空
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回栈的大小
     *
     * @return 栈的大小
     */
    public int size() {
        return N;
    }

    /**
     * 向栈中添加元素
     *
     * @param t 元素
     */
    public void push(T t) {
        // 将item添加到数组a末尾，同时将栈的长度加一
        a[N++] = t;
    }

    /**
     * 弹出最近添加的元素
     *
     * @return 元素
     */
    public T pop() {
        // 弹出元素,同时将栈的大小减一
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(20);
        In in = new In("test.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!s.equals("-")) {
                stack.push(s);
            } else {
                stack.pop();
            }
        }
        System.out.println("栈中还有" + stack.size() + "个字符串");
    }
}
