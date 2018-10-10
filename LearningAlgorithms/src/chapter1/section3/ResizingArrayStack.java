package chapter1.section3;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 * @Auther yusiming
 * @Date 2018/8/16 11:51
 */
public class ResizingArrayStack<T> implements Iterable<T> {
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
     *
     */
    public ResizingArrayStack() {
        this(DEFAULT_SIZE);
    }

    /**
     * 初始化一个大小为cap的栈
     *
     * @param cap 栈的初始化大小
     */
    @SuppressWarnings("unchecked")
    public ResizingArrayStack(int cap) {
        a = (T[]) new Object[cap];
    }

    /**
     * 往栈顶中添加元素
     *
     * @param t 被添加的元素
     */
    public void push(T t) {
        a[N++] = t;
        // 若栈的长度于数组的大小相等，扩大数组的长度为原来的2倍
        if (N == a.length) {
            resize(2 * N);
        }
    }

    /**
     * 删除并返回最近添加的元素
     *
     * @return 元素
     */
    public T pop() {
        T t = a[--N];
        // 弹出去的元素，使其变为空，避免对象游离
        a[N] = null;
        // 若栈的大小是数组的四分之一，则将数组的大小变为原来的二分之一，这样栈还有二分之一的空间使用
        if (N > 0 && N == a.length / 4) {
            resize(N * 2);
        }
        return t;
    }

    /**
     * 判断栈是否为空
     *
     * @return 若为空，返回true，否则返回false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回栈的大小
     *
     * @return 大小数值
     */
    public int size() {
        return N;
    }

    /**
     * 调整栈的大小
     *
     * @param max 调整之后的栈的大小
     */
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int i = N;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public T next() {
                return a[--i];
            }
        };
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
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
        for (String s : stack) {
            System.out.println(s);
        }
    }
}
