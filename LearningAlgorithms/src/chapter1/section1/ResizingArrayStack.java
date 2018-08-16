package chapter1.section1;

import java.util.Iterator;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 11:51
 * @Description: 使用数组实现栈，动态调整数组的大小
 */
public class ResizingArrayStack<T> implements Iterable<T> {
    // 使用一个数组实现栈
    private T[] a;
    // 栈的长度
    private int N = 0;

    public ResizingArrayStack() {
    }

    public ResizingArrayStack(int cap) {
        a = (T[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // 动态调整数组大小
    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(T t) {
        a[N++] = t;
        // 若栈的长度于数组的大小相等，扩大数组的长度为原来的2倍
        if (N == a.length) {
            resize(2 * N);
        }
    }

    public T pop() {
        T t = a[--N];
        // 弹出去的元素，使其变为空，避免对象游离
        a[N] = null;
        // 若栈的大小是数组的四分之一，则将数组的大小变为原来的二分之一，这样栈还有二分之一的空间使用
        if (N > 0 && N == a.length / 4) {
            resize(N / 2);
        }
        return t;
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
}
