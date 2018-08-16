package chapter1.section1;

import java.util.Iterator;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 13:50
 * @Description: 使用链表实现背包
 */
public class Bag<T> implements Iterable<T> {
    private int N = 0;
    private Node top = null;

    private class Node {
        T t;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void add(T t) {
        // 与栈的push方法相似
        Node oldTop = top;
        top = new Node();
        top.t = t;
        top.next = oldTop;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node current = top;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T t = current.t;
                current = current.next;
                return t;
            }
        };
    }
}
